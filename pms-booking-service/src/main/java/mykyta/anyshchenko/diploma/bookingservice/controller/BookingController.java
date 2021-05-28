package mykyta.anyshchenko.diploma.bookingservice.controller;

import mykyta.anyshchenko.diploma.adapter.request.RoomSearchRequest;
import mykyta.anyshchenko.diploma.bookingservice.model.BookingDto;
import mykyta.anyshchenko.diploma.bookingservice.model.BookingIndexRecord;
import mykyta.anyshchenko.diploma.bookingservice.service.BookingService;
import mykyta.anyshchenko.diploma.model.Room;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
@RequestMapping("booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService BookingService) {
        this.bookingService = BookingService;
    }

    @GetMapping("/{id}")
    Mono<BookingDto> getBookingById(@PathVariable String id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping
    Flux<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping
    Mono<Void> addBooking(@RequestBody BookingDto booking) {
        return bookingService.addBooking(booking);
    }

    @PutMapping
    Mono<Void> updateBooking(@RequestBody BookingDto booking) {
        return bookingService.updateBooking(booking);
    }

    @PostMapping("/find-available-rooms")
    Flux<Room> getAvailableRooms(@RequestBody RoomSearchRequest roomSearchRequest,
                                          @RequestParam @DateTimeFormat(iso = DATE) LocalDate from,
                                          @RequestParam @DateTimeFormat(iso = DATE) LocalDate to) {
        return bookingService.getAvailableRooms(roomSearchRequest, from, to);
    }

    @GetMapping("/index")
    Flux<BookingIndexRecord> getAllBookingIndexRecords() {
        return bookingService.getAllBookingIndexRecords();
    }

    @GetMapping("/is-room-available")
    Mono<Boolean> isRoomAvailableForDates(@RequestParam Integer roomId,
                                          @RequestParam @DateTimeFormat(iso = DATE) LocalDate from,
                                          @RequestParam @DateTimeFormat(iso = DATE) LocalDate to) {
        return bookingService.isRoomAvailableForDates(roomId, from, to);
    }
}
