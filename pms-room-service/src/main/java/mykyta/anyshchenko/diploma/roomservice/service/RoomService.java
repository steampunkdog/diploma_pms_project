package mykyta.anyshchenko.diploma.roomservice.service;

import mykyta.anyshchenko.diploma.model.Room;
import mykyta.anyshchenko.diploma.roomservice.model.RoomDto;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomIndexRecord;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomSearchRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomService {

    Mono<RoomDto> findById(Integer id);
    Flux<RoomDto> findAll();
    Mono<Boolean> existsById(Integer id);
    Mono<Void> addRoom(RoomDto room);
    Mono<Void> updateRoom(RoomDto room);

    Flux<Room> getRoomsByRequest(RoomSearchRequest roomSearchRequest);
}
