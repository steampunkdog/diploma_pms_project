package mykyta.anyshchenko.diploma.roomservice.service.impl;

import mykyta.anyshchenko.diploma.discovery.exception.EntityNotFoundException;
import mykyta.anyshchenko.diploma.model.RoomTag;
import mykyta.anyshchenko.diploma.roomservice.model.RoomTagDto;
import mykyta.anyshchenko.diploma.roomservice.repository.postgresql.RoomTagRepository;
import mykyta.anyshchenko.diploma.roomservice.service.RoomTagService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomTagServiceImpl implements RoomTagService {

    private final RoomTagRepository roomTagRepository;

    public RoomTagServiceImpl(RoomTagRepository RoomTagRepository) {
        this.roomTagRepository = RoomTagRepository;
    }

    @Override
    public Mono<RoomTagDto> findById(Integer id) {
        return Mono.create(
                (sink) -> {
                    if(roomTagRepository.findById(id).isPresent()) {
                        sink.success(roomTagRepository.findById(id).get());
                    } else {
                        sink.error(new EntityNotFoundException(id.toString(), RoomTag.class));
                    }
                }
        );
    }

    @Override
    public Flux<RoomTagDto> findAll() {
        return Flux.fromStream(roomTagRepository.findAll().stream());
    }

    @Override
    public Mono<Boolean> existsById(Integer id) {
        return Mono.create((sink) -> sink.success(roomTagRepository.existsById(id)));
    }

    @Override
    public Mono<Void> addRoomTag(RoomTagDto roomTag) {
        return Mono.create((sink) -> sink.success(roomTagRepository.save(roomTag))).then();
    }

    @Override
    public Mono<Void> updateRoomTag(RoomTagDto roomTag) {
        return existsById(roomTag.getId())
                .flatMap(isExist ->
                        isExist
                                ? Mono.create((sink) -> sink.success(roomTagRepository.save(roomTag))).then()
                                : Mono.error(new EntityNotFoundException(roomTag.getId().toString(), RoomTag.class))
                );
    }
    
    @Override
    public Flux<RoomTagDto> getTagsByRoomId(Integer roomId) {
        return Flux.fromStream(roomTagRepository.getTagsByRoom(roomId).stream());
    }
}
