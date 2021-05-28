package mykyta.anyshchenko.diploma.roomservice.controller;

import mykyta.anyshchenko.diploma.roomservice.model.RoomTagDto;
import mykyta.anyshchenko.diploma.roomservice.service.RoomTagService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("room-tag")
public class RoomTagController {
    
    private final RoomTagService roomTagService;

    public RoomTagController(RoomTagService roomTagService) {
        this.roomTagService = roomTagService;
    }

    @GetMapping("/{id}")
    Mono<RoomTagDto> findById(@PathVariable Integer id) {
        return roomTagService.findById(id);
    }
    
    @GetMapping
    Flux<RoomTagDto> findAll() {
        return roomTagService.findAll();
    }
    
    @GetMapping("/{id}/is-exists")
    Mono<Boolean> existsById(@PathVariable Integer id) {
        return roomTagService.existsById(id);
    }
    
    @PostMapping
    Mono<Void> addRoomTag(@RequestBody RoomTagDto RoomTag) {
        return roomTagService.addRoomTag(RoomTag);
    }
    
    @PutMapping
    Mono<Void> updateRoomTag(@RequestBody RoomTagDto RoomTag) {
        return roomTagService.updateRoomTag(RoomTag);
    }

    @GetMapping("/by-room/{roomId}")
    Flux<RoomTagDto> getTagsByRoomId(@PathVariable Integer roomId) {
        return roomTagService.getTagsByRoomId(roomId);
    }
}
