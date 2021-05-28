package mykyta.anyshchenko.diploma.bookingservice.service.impl;

import mykyta.anyshchenko.diploma.bookingservice.model.BookingIndexRecord;
import mykyta.anyshchenko.diploma.bookingservice.repository.index.BookingIndexRepository;
import mykyta.anyshchenko.diploma.bookingservice.service.BookingIndexSearchService;
import mykyta.anyshchenko.diploma.discovery.exception.EntityNotFoundException;
import mykyta.anyshchenko.diploma.model.Booking;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class BookingElasticsearchIndexSearchServiceImpl implements BookingIndexSearchService {

        private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;
        private final BookingIndexRepository bookingIndexRepository;


    public BookingElasticsearchIndexSearchServiceImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate, BookingIndexRepository bookingIndexRepository) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
        this.bookingIndexRepository = bookingIndexRepository;
    }

    @Override
    public Mono<Void> addBookingIndexRecord(BookingIndexRecord bookingIndexRecord) {
        return bookingIndexRepository.save(bookingIndexRecord).then();
    }

    @Override
    public Mono<Void> deleteBookingIndexRecord(String id) {
        return bookingIndexRepository.deleteById(id);
    }

    @Override
    public Mono<Void> updateBookingIndexRecord(BookingIndexRecord bookingIndexRecord) {
        return bookingIndexRepository.existsById(bookingIndexRecord.getId())
                .flatMap(isExist -> {
                    return isExist
                            ? bookingIndexRepository.save(bookingIndexRecord).then()
                            : Mono.error(new EntityNotFoundException(bookingIndexRecord.getId(), Booking.class));
                });
    }

    @Override
    public Flux<BookingIndexRecord> getAllBookingIndexRecords() {
        return bookingIndexRepository.findAll();
    }

    @Override
    public Mono<Long> countIntersectionBookings(Integer roomId, LocalDate from, LocalDate to) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.rangeQuery("startDate").gte(from).lt(to))
                .should(QueryBuilders.rangeQuery("endDate").gt(from).lte(to))
                .should(
                        QueryBuilders.boolQuery()
                                .must(QueryBuilders.rangeQuery("startDate").lte(from))
                                .must(QueryBuilders.rangeQuery("endDate").gt(from))
                )
                .should(
                        QueryBuilders.boolQuery()
                                .must(QueryBuilders.rangeQuery("startDate").lte(to))
                                .must(QueryBuilders.rangeQuery("endDate").gt(to))
                )
                .must(QueryBuilders.termQuery("roomId", roomId))
                .minimumShouldMatch(1);

        return reactiveElasticsearchTemplate.count(new NativeSearchQuery(queryBuilder), BookingIndexRecord.class, "booking");
    }
}
