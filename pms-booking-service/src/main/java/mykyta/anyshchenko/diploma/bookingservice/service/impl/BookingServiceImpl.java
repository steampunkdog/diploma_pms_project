package mykyta.anyshchenko.diploma.bookingservice.service.impl;

import mykyta.anyshchenko.diploma.adapter.RoomServiceAdapter;
import mykyta.anyshchenko.diploma.adapter.request.RoomSearchRequest;
import mykyta.anyshchenko.diploma.bookingservice.model.BookingDto;
import mykyta.anyshchenko.diploma.bookingservice.model.BookingIndexRecord;
import mykyta.anyshchenko.diploma.bookingservice.repository.mongo.BookingMongoRepository;
import mykyta.anyshchenko.diploma.bookingservice.service.BookingIndexSearchService;
import mykyta.anyshchenko.diploma.bookingservice.service.BookingService;
import mykyta.anyshchenko.diploma.discovery.exception.EntityNotFoundException;
import mykyta.anyshchenko.diploma.model.Booking;
import mykyta.anyshchenko.diploma.model.Room;
import mykyta.anyshchenko.diploma.model.enums.BookingStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;

@Service
@Validated
public class BookingServiceImpl implements BookingService {

    private final BookingMongoRepository bookingMongoRepository;
    private final BookingIndexSearchService bookingIndexSearchService;
    private final RoomServiceAdapter roomServiceAdapter;

    public BookingServiceImpl(BookingMongoRepository bookingMongoRepository, BookingIndexSearchService bookingIndexSearchService, RoomServiceAdapter roomServiceAdapter) {
        this.bookingMongoRepository = bookingMongoRepository;
        this.bookingIndexSearchService = bookingIndexSearchService;
        this.roomServiceAdapter = roomServiceAdapter;
    }

    @Override
    public Mono<BookingDto> getBookingById(String id) {
        return bookingMongoRepository.findById(id);
    }

    @Override
    public Flux<BookingDto> getAllBookings() {
        return bookingMongoRepository.findAll();
    }

    @Override
    @Transactional
    public Mono<Void> addBooking(@Valid BookingDto booking) {
        if (booking.getId() != null || isBookingHasFinalStatus(booking)) {
            return Mono.error(new UnsupportedOperationException("Booking must not contain id"));
        }

        return validateBookingAvailability(booking)
                .flatMap(bookingDto ->
                        bookingMongoRepository.save(bookingDto)
                                .map(BookingIndexRecord::new)
                                .flatMap(bookingIndexSearchService::addBookingIndexRecord)
                                .then()
                );
    }

    @Override
    public Mono<Void> updateBooking(@Valid BookingDto booking) {
        return bookingMongoRepository.existsById(booking.getId())
                .flatMap(isExist ->
                        isExist
                                ? bookingMongoRepository.save(booking)
                                : Mono.error(new EntityNotFoundException(booking.getId(), Booking.class))
                )
                .flatMap(bookingDto ->
                        isBookingHasFinalStatus(bookingDto)
                                ? bookingIndexSearchService.addBookingIndexRecord(new BookingIndexRecord(bookingDto)).then()
                                : bookingIndexSearchService.deleteBookingIndexRecord(bookingDto.getId())
                );
    }

    @Override
    public Flux<BookingIndexRecord> getAllBookingIndexRecords() {
        return bookingIndexSearchService.getAllBookingIndexRecords();
    }

    @Override
    public Flux<Room> getAvailableRooms(RoomSearchRequest roomSearchRequest, LocalDate from, LocalDate to) {
        return roomServiceAdapter.getRoomsByRequest(roomSearchRequest)
                .filterWhen(room -> isRoomAvailableForDates(room.getId(), from, to));
    }

    @Override
    public Mono<Boolean> isRoomAvailableForDates(Integer roomId, LocalDate from, LocalDate to) {
        return bookingIndexSearchService.countIntersectionBookings(roomId, from, to)
                .map(aLong -> aLong == 0);
    }

    private boolean isBookingHasFinalStatus(BookingDto bookingDto) {
        return bookingDto.getStatus().equals(BookingStatus.CANCELED) || bookingDto.getStatus().equals(BookingStatus.COMPLETED);
    }

    private Mono<BookingDto> validateBookingAvailability(BookingDto booking) {
        if (booking.getStartDate().isBefore(LocalDate.now()) || booking.getEndDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Booking dates cannot be before current date");
        }
        return isRoomAvailableForDates(booking.getRoomId(), booking.getStartDate(), booking.getEndDate())
                .flatMap(isAvailable ->
                        isAvailable
                                ? Mono.fromSupplier(() -> booking)
                                : Mono.error(new UnsupportedOperationException(String.format("Cannot book room=%s for dates {%s - %s}. room is already booked", booking.getRoomId(), booking.getStartDate(), booking.getEndDate())))
                );
    }
}
