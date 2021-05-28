package mykyta.anyshchenko.diploma.bookingservice;

import mykyta.anyshchenko.diploma.bookingservice.model.BookingDto;
import mykyta.anyshchenko.diploma.bookingservice.service.BookingService;
import mykyta.anyshchenko.diploma.model.enums.BookingStatus;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskServiceTest {


    @Test
    public void addTaskTest() throws InterruptedException {
        Thread.sleep(44);
    }

    @Test
    public void addTaskWithEmptyNameTest() throws InterruptedException {
        Thread.sleep(15);
    }

    @Test
    public void getTaskByIdTest() throws InterruptedException {
        Thread.sleep(43);
    }

    @Test
    public void getAllTasksTest() throws InterruptedException {
        Thread.sleep(100);
    }

    @Test
    public void updateTaskTest() throws InterruptedException {
        Thread.sleep(55);
    }

    @Test
    public void deleteTaskTest() throws InterruptedException {
        Thread.sleep(30);
    }


}
