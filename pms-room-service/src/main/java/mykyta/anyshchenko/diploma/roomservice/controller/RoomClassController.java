package mykyta.anyshchenko.diploma.roomservice.controller;

import mykyta.anyshchenko.diploma.roomservice.model.RoomClassDto;
import mykyta.anyshchenko.diploma.roomservice.service.RoomClassService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("room-class")
public class RoomClassController {
    
    private final RoomClassService roomClassService;

    public RoomClassController(RoomClassService roomClassService) {
        this.roomClassService = roomClassService;
    }

    @GetMapping("/{id}")
    Mono<RoomClassDto> findById(@PathVariable Integer id) {
        return roomClassService.findById(id);
    }
    
    @GetMapping
    Flux<RoomClassDto> findAll() {
        return roomClassService.findAll();
    }
    
    @GetMapping("/{id}/is-exists")
    Mono<Boolean> existsById(@PathVariable Integer id) {
        return roomClassService.existsById(id);
    }
    
    @PostMapping
    Mono<Void> addRoomClass(@RequestBody RoomClassDto RoomClass) {
        return roomClassService.addRoomClass(RoomClass);
    }
    
    @PutMapping
    Mono<Void> updateRoomClass(@RequestBody RoomClassDto RoomClass) {
        return roomClassService.updateRoomClass(RoomClass);
    }
}
