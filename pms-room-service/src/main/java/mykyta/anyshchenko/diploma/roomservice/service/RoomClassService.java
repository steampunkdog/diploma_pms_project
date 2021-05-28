package mykyta.anyshchenko.diploma.roomservice.service;

import mykyta.anyshchenko.diploma.roomservice.model.RoomClassDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomClassService {
    Mono<RoomClassDto> findById(Integer id);
    Flux<RoomClassDto> findAll();
    Mono<Boolean> existsById(Integer id);
    Mono<Void> addRoomClass(RoomClassDto roomClass);
    Mono<Void> updateRoomClass(RoomClassDto roomClass);

}
