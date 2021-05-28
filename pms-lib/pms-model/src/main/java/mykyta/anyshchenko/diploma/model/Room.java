package mykyta.anyshchenko.diploma.model;

import java.util.Collection;

public interface Room {
    Integer getId();
    String getName();
    String getDescription();
    RoomClass getRoomClass();
    int getFloor();
    int getRoomArea();
    int getNumberOfRooms();
    int getSleepingAreaNumber();
    float getPrice();
    Collection<? extends RoomTag> getRoomTags();

}
