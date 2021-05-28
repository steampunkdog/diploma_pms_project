package mykyta.anyshchenko.diploma.roomservice.controller;

import mykyta.anyshchenko.diploma.model.Room;
import mykyta.anyshchenko.diploma.roomservice.model.RoomDto;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomSearchRequest;
import mykyta.anyshchenko.diploma.roomservice.service.RoomIndexService;
import mykyta.anyshchenko.diploma.roomservice.service.RoomService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("room")
public class RoomController {

    private final RoomService roomService;
    private final RoomIndexService roomIndexService;

    public RoomController(RoomService roomService, RoomIndexService roomIndexService) {
        this.roomService = roomService;
        this.roomIndexService = roomIndexService;
    }

    @GetMapping("/{id}")
    Mono<RoomDto> findById(@PathVariable Integer id) {
        return roomService.findById(id);
    }

    @GetMapping
    Flux<RoomDto> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}/is-exists")
    Mono<Boolean> existsById(@PathVariable Integer id) {
        return roomService.existsById(id);
    }

    @PostMapping
    Mono<Void> addRoom(@RequestBody RoomDto room) {
        return roomService.addRoom(room);
    }

    @PutMapping
    Mono<Void> updateRoom(@RequestBody RoomDto room) {
        return roomService.updateRoom(room);
    }

    @PostMapping("/search")
    Flux<Room> getRoomsByRequest(@RequestBody RoomSearchRequest roomSearchRequest) {
        return roomService.getRoomsByRequest(roomSearchRequest);
    }

    @PostMapping("/search/ids")
    Flux<Integer> getRoomIdsByRequest(@RequestBody RoomSearchRequest roomSearchRequest) {
        return roomIndexService.getRoomIdsByRequest(roomSearchRequest);
    }
}
