package mykyta.anyshchenko.diploma.bookingservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import mykyta.anyshchenko.diploma.model.Booking;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalDate;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;
import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;

@Document(indexName = "booking")
public class BookingIndexRecord {

    @Id
    @Field(type = Keyword)
    private String id;

    private Integer roomId;

    @Field(type = Date, format = DateFormat.date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private LocalDate startDate;

    @Field(type = Date, format = DateFormat.date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private LocalDate endDate;

    public BookingIndexRecord() {
    }


    public BookingIndexRecord(Booking booking){
        this.id = booking.getId();
        this.roomId = booking.getRoomId();
        this.startDate = booking.getStartDate();
        this.endDate = booking.getEndDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
