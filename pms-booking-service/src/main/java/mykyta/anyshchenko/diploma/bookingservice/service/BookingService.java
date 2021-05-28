package mykyta.anyshchenko.diploma.bookingservice.service;

import mykyta.anyshchenko.diploma.adapter.request.RoomSearchRequest;
import mykyta.anyshchenko.diploma.bookingservice.model.BookingDto;
import mykyta.anyshchenko.diploma.bookingservice.model.BookingIndexRecord;
import mykyta.anyshchenko.diploma.model.Room;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;

public interface BookingService {

    Mono<BookingDto> getBookingById(String id);
    Flux<BookingDto> getAllBookings();
    Mono<Void> addBooking(@Valid BookingDto booking);
    Mono<Void> updateBooking(@Valid BookingDto booking);

    Flux<Room> getAvailableRooms(RoomSearchRequest roomSearchRequest, LocalDate from, LocalDate to);
    Flux<BookingIndexRecord> getAllBookingIndexRecords();
    Mono<Boolean> isRoomAvailableForDates(Integer roomId, LocalDate from, LocalDate to);
}
