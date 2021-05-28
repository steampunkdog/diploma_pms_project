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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingAvailabilityCheckTest {

    @Autowired
    public BookingService bookingService;


    @Test
    public void isRoomAvailableForDatesWithAvailableDatesTest() {
        BookingDto bookingDto = generateDefaultBooking();
        bookingDto.setRoomId(201);

        bookingService.addBooking(bookingDto).block();

        Boolean isAvailable = bookingService.isRoomAvailableForDates(
                201,
                LocalDate.of(2022, 12, 9),
                LocalDate.of(2022, 12, 15)
        ).block();

        Assert.assertTrue(isAvailable);
    }

    @Test
    public void isRoomAvailableForDatesWithClaseDatesTest() {
        BookingDto bookingDto = generateDefaultBooking();
        bookingDto.setRoomId(202);

        bookingService.addBooking(bookingDto).block();

        Boolean isAvailable = bookingService.isRoomAvailableForDates(
                202,
                LocalDate.of(2021, 12, 16),
                LocalDate.of(2021, 12, 18)
        ).block();

        Assert.assertTrue(isAvailable);
    }

    @Test
    public void isRoomAvailableForDatesWithUnavailableDatesTest() {
        BookingDto bookingDto = generateDefaultBooking();
        bookingDto.setRoomId(200);

        bookingService.addBooking(bookingDto).block();

        Boolean isAvailable = bookingService.isRoomAvailableForDates(
                200,
                LocalDate.of(2021, 12, 9),
                LocalDate.of(2021, 12, 15)
        ).block();

        Assert.assertFalse(isAvailable);
    }

    public static BookingDto generateDefaultBooking() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setUserId("aaa");
        bookingDto.setRoomId(1);
        bookingDto.setPrice(1000);
        bookingDto.setStatus(BookingStatus.PAYED);
        bookingDto.setStartDate(LocalDate.of(2021, 12, 10));
        bookingDto.setEndDate(LocalDate.of(2021, 12, 15));

        return bookingDto;
    }
}
