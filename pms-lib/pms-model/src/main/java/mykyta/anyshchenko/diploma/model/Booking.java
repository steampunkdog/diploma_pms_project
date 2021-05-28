package mykyta.anyshchenko.diploma.model;

import mykyta.anyshchenko.diploma.model.enums.BookingStatus;

import java.time.LocalDate;

public interface Booking {

    String getId();
    String getUserId();
    Integer getRoomId();
    BookingStatus getStatus();
    LocalDate getStartDate();
    LocalDate getEndDate();
    Integer getPrice();

}
