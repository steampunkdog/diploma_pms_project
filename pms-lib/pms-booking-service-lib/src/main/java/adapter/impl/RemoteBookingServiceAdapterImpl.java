package adapter.impl;

import adapter.BookingServiceAdapter;
import mykyta.anyshchenko.diploma.discovery.ServiceWebClientProvider;
import mykyta.anyshchenko.diploma.model.Booking;
import mykyta.anyshchenko.diploma.model.enums.BookingStatus;
import mykyta.anyshchenko.diploma.model.enums.TaskStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class RemoteBookingServiceAdapterImpl implements BookingServiceAdapter {

    public static final String USER_SERVICE_ID = "pms-booking-service";

    private WebClient webClient;

    public RemoteBookingServiceAdapterImpl(ServiceWebClientProvider serviceWebClientProvider) {
        this.webClient = serviceWebClientProvider.buildWebClientForService(USER_SERVICE_ID);
    }

    @Override
    public Mono<Booking> getBookingById(String id) {
        return webClient
                .get()
                .uri("/booking/" + id)
                .retrieve()
                .bodyToMono(BookingDto.class)
                .cast(Booking.class);
    }

    @Override
    public Flux<Booking> getAllBookings() {
        return webClient
                .get()
                .uri("/booking")
                .retrieve()
                .bodyToFlux(BookingDto.class)
                .cast(Booking.class);
    }


    static class BookingDto implements Booking {

        private String id;
        private String userId;
        private Integer roomId;
        private BookingStatus status;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer price;

        @Override
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public Integer getRoomId() {
            return roomId;
        }

        public void setRoomId(Integer roomId) {
            this.roomId = roomId;
        }

        @Override
        public BookingStatus getStatus() {
            return status;
        }

        public void setStatus(BookingStatus status) {
            this.status = status;
        }

        @Override
        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        @Override
        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        @Override
        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }

}
