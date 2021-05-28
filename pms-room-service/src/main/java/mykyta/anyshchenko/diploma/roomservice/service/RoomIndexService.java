package mykyta.anyshchenko.diploma.roomservice.service;

import mykyta.anyshchenko.diploma.roomservice.model.RoomDto;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomIndexRecord;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomSearchRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomIndexService {

    Mono<RoomIndexRecord> findById(Integer id);
    Flux<RoomIndexRecord> findAll();
    Mono<Boolean> existsById(Integer id);
    Mono<Void> save(RoomIndexRecord room);

    Flux<Integer> getRoomIdsByRequest(RoomSearchRequest roomSearchRequest);

    Flux<RoomIndexRecord> getRoomIndexRecordsByRequest(RoomSearchRequest roomSearchRequest);
}
