package mykyta.anyshchenko.diploma.roomservice.service.impl;

import mykyta.anyshchenko.diploma.discovery.exception.EntityNotFoundException;
import mykyta.anyshchenko.diploma.model.RoomClass;
import mykyta.anyshchenko.diploma.roomservice.model.RoomClassDto;
import mykyta.anyshchenko.diploma.roomservice.repository.postgresql.RoomClassRepository;
import mykyta.anyshchenko.diploma.roomservice.service.RoomClassService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomClassServiceImpl implements RoomClassService {

    private final RoomClassRepository roomClassRepository;

    public RoomClassServiceImpl(RoomClassRepository roomClassRepository) {
        this.roomClassRepository = roomClassRepository;
    }

    @Override
    public Mono<RoomClassDto> findById(Integer id) {
        return Mono.create(
                (sink) -> {
                    if(roomClassRepository.findById(id).isPresent()) {
                        sink.success(roomClassRepository.findById(id).get());
                    } else {
                        sink.error(new EntityNotFoundException(id.toString(), RoomClass.class));
                    }
                }
        );
    }

    @Override
    public Flux<RoomClassDto> findAll() {
        return Flux.fromStream(roomClassRepository.findAll().stream());
    }

    @Override
    public Mono<Boolean> existsById(Integer id) {
        return Mono.create((sink) -> sink.success(roomClassRepository.existsById(id)));
    }

    @Override
    public Mono<Void> addRoomClass(RoomClassDto roomClass) {
        return Mono.create((sink) -> sink.success(roomClassRepository.save(roomClass))).then();
    }

    @Override
    public Mono<Void> updateRoomClass(RoomClassDto roomClass) {
        return existsById(roomClass.getId())
                .flatMap(isExist ->
                        isExist
                            ? Mono.create((sink) -> sink.success(roomClassRepository.save(roomClass))).then()
                            : Mono.error(new EntityNotFoundException(roomClass.getId().toString(), RoomClass.class))
                );
    }
}
