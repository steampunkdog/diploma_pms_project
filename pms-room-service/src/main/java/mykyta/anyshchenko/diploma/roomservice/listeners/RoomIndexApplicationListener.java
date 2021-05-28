package mykyta.anyshchenko.diploma.roomservice.listeners;

import mykyta.anyshchenko.diploma.roomservice.model.RoomTagDto;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomIndexRecord;
import mykyta.anyshchenko.diploma.roomservice.repository.elasticsearch.RoomIndexRepository;
import mykyta.anyshchenko.diploma.roomservice.repository.postgresql.RoomRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Component
public class RoomIndexApplicationListener implements ApplicationListener<ApplicationStartedEvent> {

    private final RoomRepository roomRepository;
    private final RoomIndexRepository roomIndexRepository;

    public RoomIndexApplicationListener(RoomRepository roomRepository, RoomIndexRepository roomIndexRepository) {
        this.roomRepository = roomRepository;
        this.roomIndexRepository = roomIndexRepository;
    }

    @Override
    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent event) {
        roomIndexRepository.saveAll(
                Flux.fromStream(roomRepository.findAll().stream())
                    .map(RoomIndexRecord::new)
        )
        .then()
        .block();
    }
}
