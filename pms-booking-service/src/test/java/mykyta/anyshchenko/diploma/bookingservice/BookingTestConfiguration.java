package mykyta.anyshchenko.diploma.bookingservice;

import mykyta.anyshchenko.diploma.adapter.RoomServiceAdapter;
import mykyta.anyshchenko.diploma.bookingservice.model.BookingIndexRecord;
import mykyta.anyshchenko.diploma.bookingservice.repository.index.BookingIndexRepository;
import mykyta.anyshchenko.diploma.discovery.ServiceWebClientProvider;
import mykyta.anyshchenko.diploma.model.Room;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

@TestConfiguration
public class BookingTestConfiguration {


    @Bean
    @Primary
    public RoomServiceAdapter roomServiceAdapter() {
        RoomServiceAdapter roomServiceAdapter = Mockito.mock(RoomServiceAdapter.class);
        Mockito.when(roomServiceAdapter.getRoomsByRequest(any())).then(invocationOnMock -> Flux.empty());
        return roomServiceAdapter;
    }

    @Bean
    @Primary
    public ServiceWebClientProvider serviceWebClientProvider() {
        return Mockito.mock(ServiceWebClientProvider.class);
    }
}
