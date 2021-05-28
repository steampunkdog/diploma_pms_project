package mykyta.anyshchenko.diploma.roomservice.model;

import mykyta.anyshchenko.diploma.model.Room;
import mykyta.anyshchenko.diploma.model.RoomTag;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "room")
public class RoomDto implements Room {

    @Id
    private Integer id;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_class")
    private RoomClassDto roomClass;

    @Column(name = "room_floor")
    private int floor;

    @Column(name = "room_area")
    private int roomArea;

    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    @Column(name = "sleeping_area_number")
    private int sleepingAreaNumber;

    private float price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RoomToRoomTag",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "room_tag_id")
    )
    private Collection<RoomTagDto> roomTags;

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

    @Override
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
