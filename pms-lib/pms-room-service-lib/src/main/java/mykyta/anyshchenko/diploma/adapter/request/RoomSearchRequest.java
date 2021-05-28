package mykyta.anyshchenko.diploma.adapter.request;

import java.util.ArrayList;

public class RoomSearchRequest {

    private Integer roomClassId;
    private Integer numberOfRooms;
    private Integer sleepingAreaNumber;
    private ArrayList<Integer> tagIds;

    public Integer getRoomClassId() {
        return roomClassId;
    }

    public void setRoomClassId(Integer roomClassId) {
        this.roomClassId = roomClassId;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getSleepingAreaNumber() {
        return sleepingAreaNumber;
    }

    public void setSleepingAreaNumber(Integer sleepingAreaNumber) {
        this.sleepingAreaNumber = sleepingAreaNumber;
    }

    public ArrayList<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(ArrayList<Integer> tagIds) {
        this.tagIds = tagIds;
    }
}
