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
public class BookingServiceTest {

    @Autowired
    public BookingService bookingService;


    @Test
    public void addBookingTest() {
        BookingDto bookingDto = generateDefaultBooking();
        bookingDto.setRoomId(10000000);

        BookingDto bookingFromDB = bookingService.addBooking(bookingDto)
                .thenMany(bookingService.getAllBookings())
                .filter(booking -> booking.getRoomId().equals(10000000))
                .blockFirst();

        Assert.assertNotNull(bookingFromDB);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addBookingWithInvalidStatusTest() {
        BookingDto bookingDto = generateDefaultBooking();
        bookingDto.setRoomId(10000000);
        bookingDto.setStatus(BookingStatus.CANCELED);

        bookingService.addBooking(bookingDto).block();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addBookingWithIntersectedDatesAndRoomTest() {
        BookingDto bookingWithValidDatesDto = generateDefaultBooking();
        bookingWithValidDatesDto.setRoomId(122);

        BookingDto bookingWithIntersectedDatesDto = generateDefaultBooking();
        bookingWithIntersectedDatesDto.setRoomId(122);
        bookingWithIntersectedDatesDto.setEndDate(LocalDate.of(2021, 12, 16));

        bookingService.addBooking(bookingWithValidDatesDto)
                .then(bookingService.addBooking(bookingWithIntersectedDatesDto))
                .block();
    }

    @Test
    public void updateBookingTest() {
        BookingDto bookingDto = generateDefaultBooking();
        bookingDto.setRoomId(100);

        BookingDto bookingFromDB = bookingService.addBooking(bookingDto)
                .thenMany(bookingService.getAllBookings())
                .filter(booking -> booking.getRoomId().equals(100))
                .blockFirst();

        Assert.assertNotNull(bookingFromDB);

        bookingFromDB.setPrice(10000);

        BookingDto updatedBookingFromDB = bookingService.updateBooking(bookingFromDB)
                .thenMany(bookingService.getAllBookings())
                .filter(booking -> booking.getRoomId().equals(100))
                .blockFirst();

        Assert.assertEquals(bookingFromDB.getPrice(), updatedBookingFromDB.getPrice());
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
