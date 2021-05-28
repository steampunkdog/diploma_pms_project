package mykyta.anyshchenko.diploma.bookingservice.listener;

import mykyta.anyshchenko.diploma.bookingservice.model.BookingIndexRecord;
import mykyta.anyshchenko.diploma.bookingservice.repository.index.BookingIndexRepository;
import mykyta.anyshchenko.diploma.bookingservice.repository.mongo.BookingMongoRepository;
import mykyta.anyshchenko.diploma.bookingservice.service.BookingService;
import mykyta.anyshchenko.diploma.model.enums.BookingStatus;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

@Component
public class ElasticAppStartupListener implements ApplicationListener<ApplicationStartedEvent> {

    public BookingMongoRepository bookingMongoRepository;
    public BookingIndexRepository bookingIndexRepository;

    public ElasticAppStartupListener(BookingMongoRepository bookingMongoRepository, BookingIndexRepository bookingIndexRepository) {
        this.bookingMongoRepository = bookingMongoRepository;
        this.bookingIndexRepository = bookingIndexRepository;
    }

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        bookingIndexRepository.deleteAll()
                .flatMapMany(v -> bookingMongoRepository.findAll())
                .filter(bookingDto -> {
                    BookingStatus status = bookingDto.getStatus();
                    boolean notCompleted = !(status.equals(BookingStatus.CANCELED) || status.equals(BookingStatus.COMPLETED));
                    boolean notFinishedByDate = !bookingDto.getEndDate().isBefore(LocalDate.now());
                    return notCompleted && notFinishedByDate;
                })
                .map(BookingIndexRecord::new)
                .flatMap(bookingIndexRepository::save)
                .then()
                .block();

        BookingIndexRecord bookingIndexRecord = new BookingIndexRecord();
        bookingIndexRecord.setId("2323");
        bookingIndexRecord.setStartDate(LocalDate.ofYearDay(2021, 120));
        bookingIndexRecord.setEndDate(LocalDate.ofYearDay(2021, 129));
        bookingIndexRecord.setRoomId(3);
        bookingIndexRepository.save(bookingIndexRecord).block();

    }
}
