package mykyta.anyshchenko.diploma.bookingservice;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomServiceTest {

    private Random random = new Random();

    @Test
    public void addRoomTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addRoomWithEmptyFloorTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addRoomWithEmptyIdTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addRoomWithEmptySleepingAreaTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void getRoomByIdTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void getAllRoomsTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void updateRoomTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void deleteRoomTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

}
