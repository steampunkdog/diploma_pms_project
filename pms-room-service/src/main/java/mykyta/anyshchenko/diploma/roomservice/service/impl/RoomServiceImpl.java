package mykyta.anyshchenko.diploma.roomservice.service.impl;

import mykyta.anyshchenko.diploma.discovery.exception.EntityNotFoundException;
import mykyta.anyshchenko.diploma.model.Room;
import mykyta.anyshchenko.diploma.roomservice.model.RoomDto;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomIndexRecord;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomSearchRequest;
import mykyta.anyshchenko.diploma.roomservice.repository.postgresql.RoomRepository;
import mykyta.anyshchenko.diploma.roomservice.service.RoomIndexService;
import mykyta.anyshchenko.diploma.roomservice.service.RoomService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomIndexService roomIndexService;

    public RoomServiceImpl(RoomRepository roomRepository, RoomIndexService roomIndexService) {
        this.roomRepository = roomRepository;
        this.roomIndexService = roomIndexService;
    }

    @Override
    public Mono<RoomDto> findById(Integer id) {
        return Mono.create(
                (sink) -> {
                    if(roomRepository.findById(id).isPresent()) {
                        sink.success(roomRepository.findById(id).get());
                    } else {
                        sink.error(new EntityNotFoundException(id.toString(), Room.class));
                    }
                }
        );
    }

    @Override
    public Flux<RoomDto> findAll() {
        return Flux.fromStream(roomRepository.findAll().stream());
    }

    @Override
    public Mono<Boolean> existsById(Integer id) {
        return Mono.create((sink) -> sink.success(roomRepository.existsById(id)));
    }

    @Override
    public Mono<Void> addRoom(RoomDto room) {
        return Mono.just(roomRepository.save(room))
                .flatMap(roomDto -> roomIndexService.save(new RoomIndexRecord(roomDto)));
    }

    @Override
    public Mono<Void> updateRoom(RoomDto room) {
        return existsById(room.getId())
                .flatMap(isExist ->
                        isExist
                                ? Mono.just(roomRepository.save(room))
                                : Mono.error(new EntityNotFoundException(room.getId().toString(), Room.class))
                )
                .flatMap(roomDto -> roomIndexService.save(new RoomIndexRecord(roomDto)));
    }

    @Override
    public Flux<Room> getRoomsByRequest(RoomSearchRequest roomSearchRequest) {
        return roomIndexService.getRoomIdsByRequest(roomSearchRequest)
                .flatMap(this::findById);
    }
}
