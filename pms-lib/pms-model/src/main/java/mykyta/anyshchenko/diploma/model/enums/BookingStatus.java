package mykyta.anyshchenko.diploma.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BookingStatus extends DynamicEnum<BookingStatus> {
    public static final BookingStatus REQUESTED = new BookingStatus("requested");
    public static final BookingStatus CONFIRMED = new BookingStatus("confirmed");
    public static final BookingStatus IN_PROGRESS = new BookingStatus("in progress");
    public static final BookingStatus PAYED = new BookingStatus("payed");
    public static final BookingStatus COMPLETED = new BookingStatus("completed");
    public static final BookingStatus CANCELED = new BookingStatus("canceled");

    private BookingStatus(String value) {
        super(value);
    }

    @JsonCreator
    public static BookingStatus create(String bookingStatus) {
        return new BookingStatus(bookingStatus);
    }
}
