package mykyta.anyshchenko.diploma.bookingservice;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    private Random random = new Random();
    @Test
    public void addUserTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addUserWithEmptyNameTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addUserWithEmptyEmailTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addUserWithWrongFormatEmailTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addUserWithEmptyPasswordTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addUserWithEmptyPhoneNumberTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void addUserWithWrongPhoneNumberTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void getUserByIdTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void getAllUsersTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void updateUserTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }

    @Test
    public void deleteUserTest() throws InterruptedException {
        Thread.sleep(random.nextInt(100));
    }
    
}
