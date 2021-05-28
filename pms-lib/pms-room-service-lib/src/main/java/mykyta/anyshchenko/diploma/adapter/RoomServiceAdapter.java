package mykyta.anyshchenko.diploma.adapter;


import mykyta.anyshchenko.diploma.adapter.request.RoomSearchRequest;
import mykyta.anyshchenko.diploma.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomServiceAdapter {

    Mono<Room> getRoomById(Integer id);
    Flux<Room> getAllRooms();
    Flux<Room> getRoomsByRequest(RoomSearchRequest request);

    Mono<RoomClass> getRoomClassById(Integer id);
    Flux<RoomClass> getAllRoomClasses();

    Mono<RoomTag> getRoomTagById(Integer id);
    Flux<RoomTag> getAllRoomTags();
    Flux<RoomTag> getTagsByRoomId(Integer roomId);


}
