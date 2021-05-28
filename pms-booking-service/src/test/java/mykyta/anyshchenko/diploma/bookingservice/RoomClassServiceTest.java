package mykyta.anyshchenko.diploma.bookingservice;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomClassServiceTest {

    private Random random = new Random();
    @Test
    public void addRoomClassTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addRoomClassWithEmptyNameTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void getRoomClassByIdTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void getAllRoomClasssTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void updateRoomClassTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void deleteRoomClassTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }
    
}
