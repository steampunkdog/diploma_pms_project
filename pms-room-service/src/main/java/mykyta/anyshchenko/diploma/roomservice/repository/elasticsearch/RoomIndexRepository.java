package mykyta.anyshchenko.diploma.roomservice.repository.elasticsearch;

import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomIndexRecord;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomIndexRepository extends ReactiveCrudRepository<RoomIndexRecord, Integer> {
}
