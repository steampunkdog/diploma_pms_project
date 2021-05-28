package mykyta.anyshchenko.diploma.bookingservice.repository.index;


import mykyta.anyshchenko.diploma.bookingservice.model.BookingIndexRecord;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingIndexRepository extends ReactiveCrudRepository<BookingIndexRecord, String> {
}
