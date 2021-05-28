package mykyta.anyshchenko.diploma.roomservice.service;

import mykyta.anyshchenko.diploma.roomservice.model.RoomTagDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomTagService {

    Mono<RoomTagDto> findById(Integer id);
    Flux<RoomTagDto> findAll();
    Mono<Boolean> existsById(Integer id);
    Mono<Void> addRoomTag(RoomTagDto roomTag);
    Mono<Void> updateRoomTag(RoomTagDto roomTag);
    Flux<RoomTagDto> getTagsByRoomId(Integer roomId);
}
