package mykyta.anyshchenko.diploma.bookingservice.service;

import mykyta.anyshchenko.diploma.bookingservice.model.BookingIndexRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface BookingIndexSearchService {

    Mono<Void> addBookingIndexRecord(BookingIndexRecord bookingIndexRecord);
    Mono<Void> deleteBookingIndexRecord(String id);
    Mono<Void> updateBookingIndexRecord(BookingIndexRecord bookingIndexRecord);
    Flux<BookingIndexRecord> getAllBookingIndexRecords();

    Mono<Long> countIntersectionBookings(Integer roomId, LocalDate from, LocalDate to);
}
