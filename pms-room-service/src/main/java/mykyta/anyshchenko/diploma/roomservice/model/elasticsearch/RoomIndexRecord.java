package mykyta.anyshchenko.diploma.roomservice.model.elasticsearch;

import mykyta.anyshchenko.diploma.model.Room;
import mykyta.anyshchenko.diploma.model.RoomTag;
import mykyta.anyshchenko.diploma.roomservice.model.RoomTagDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document(indexName = "room-index")
public class RoomIndexRecord {
    @Id
    private Integer id;
    private int roomClassId;
    private int numberOfRooms;
    private int sleepingAreaNumber;
    private List<Integer> tagIds;

    public RoomIndexRecord() {
    }

    public RoomIndexRecord(Room room) {
        setId(room.getId());
        setNumberOfRooms(room.getNumberOfRooms());
        setSleepingAreaNumber(room.getSleepingAreaNumber());
        setRoomClassId(room.getRoomClass().getId());
        setTagIds(
                CollectionUtils.emptyIfNull(room.getRoomTags()).stream()
                        .map(RoomTag::getId)
                        .collect(Collectors.toList())
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRoomClassId() {
        return roomClassId;
    }

    public void setRoomClassId(int roomClassId) {
        this.roomClassId = roomClassId;
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

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }
}
