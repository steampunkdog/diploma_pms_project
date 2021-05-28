package mykyta.anyshchenko.diploma.adapter.impl;

import mykyta.anyshchenko.diploma.adapter.RoomServiceAdapter;
import mykyta.anyshchenko.diploma.adapter.request.RoomSearchRequest;
import mykyta.anyshchenko.diploma.discovery.ServiceWebClientProvider;
import mykyta.anyshchenko.diploma.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Service
public class RemoteRoomServiceAdapterImpl implements RoomServiceAdapter {

    public static final String USER_SERVICE_ID = "pms-room-service";

    private WebClient webClient;

    public RemoteRoomServiceAdapterImpl(ServiceWebClientProvider serviceWebClientProvider) {
        this.webClient = serviceWebClientProvider.buildWebClientForService(USER_SERVICE_ID);
    }

    @Override
    public Mono<Room> getRoomById(Integer id) {
        return webClient
                .get()
                .uri("/room/" + id)
                .retrieve()
                .bodyToMono(RoomDto.class)
                .cast(Room.class);
    }

    @Override
    public Flux<Room> getAllRooms() {
        return webClient
                .get()
                .uri("/room")
                .retrieve()
                .bodyToFlux(RoomDto.class)
                .cast(Room.class);
    }

    @Override
    public Flux<Room> getRoomsByRequest(RoomSearchRequest request) {
        return webClient
                .post()
                .uri("/room/search")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(RoomDto.class)
                .cast(Room.class);
    }

    @Override
    public Mono<RoomClass> getRoomClassById(Integer id) {
        return webClient
                .get()
                .uri("/room-class/" + id)
                .retrieve()
                .bodyToMono(RoomClassDto.class)
                .cast(RoomClass.class);
    }

    @Override
    public Flux<RoomClass> getAllRoomClasses() {
        return webClient
                .get()
                .uri("/room-class")
                .retrieve()
                .bodyToFlux(RoomClassDto.class)
                .cast(RoomClass.class);
    }

    @Override
    public Mono<RoomTag> getRoomTagById(Integer id) {
        return webClient
                .get()
                .uri("/room-tag/" + id)
                .retrieve()
                .bodyToMono(RoomTagDto.class)
                .cast(RoomTag.class);
    }

    @Override
    public Flux<RoomTag> getAllRoomTags() {
        return webClient
                .get()
                .uri("/room-tag")
                .retrieve()
                .bodyToFlux(RoomTagDto.class)
                .cast(RoomTag.class);
    }

    @Override
    public Flux<RoomTag> getTagsByRoomId(Integer roomId) {
        return webClient
                .get()
                .uri("/room-tag/by-room/" + roomId)
                .retrieve()
                .bodyToFlux(RoomTagDto.class)
                .cast(RoomTag.class);
    }

    static class RoomClassDto implements RoomClass {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class RoomTagDto implements RoomTag {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    static class RoomDto implements Room{

        private Integer id;
        private String name;
        private String description;
        private RoomClassDto roomClass;
        private int floor;
        private int roomArea;
        private int numberOfRooms;
        private int sleepingAreaNumber;
        private Collection<RoomTagDto> roomTags;
        private float price;


        @Override
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public RoomClassDto getRoomClass() {
            return roomClass;
        }

        public void setRoomClass(RoomClassDto roomClass) {
            this.roomClass = roomClass;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getRoomArea() {
            return roomArea;
        }

        public void setRoomArea(int roomArea) {
            this.roomArea = roomArea;
        }

        public int getNumberOfRooms() {
            return numberOfRooms;
        }

        public void setNumberOfRooms(int numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
        }

        public int getSleepingAreaNumber() {
            return sleepingAreaNumber;
        }

        public void setSleepingAreaNumber(int sleepingAreaNumber) {
            this.sleepingAreaNumber = sleepingAreaNumber;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        @Override
        public Collection<RoomTagDto> getRoomTags() {
            return roomTags;
        }

        public void setRoomTags(Collection<RoomTagDto> roomTags) {
            this.roomTags = roomTags;
        }
    }

}
