package mykyta.anyshchenko.diploma.bookingservice;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomTagServiceTest {

    private Random random = new Random();
    @Test
    public void addRoomTagTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addRoomTagWithEmptyNameTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void getRoomTagByIdTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void getAllRoomTagsTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void updateRoomTagTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void deleteRoomTagTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }
    
}
