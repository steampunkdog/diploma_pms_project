package adapter;


import mykyta.anyshchenko.diploma.model.Booking;
import mykyta.anyshchenko.diploma.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingServiceAdapter {

    Mono<Booking> getBookingById(String id);
    Flux<Booking> getAllBookings();

}
