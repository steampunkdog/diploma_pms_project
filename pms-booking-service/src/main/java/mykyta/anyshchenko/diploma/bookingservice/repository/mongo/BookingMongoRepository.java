package mykyta.anyshchenko.diploma.bookingservice.repository.mongo;

import mykyta.anyshchenko.diploma.bookingservice.model.BookingDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingMongoRepository extends ReactiveMongoRepository<BookingDto, String> {
}
