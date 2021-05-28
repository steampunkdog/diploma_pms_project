package mykyta.anyshchenko.diploma.roomservice.service.impl;

import mykyta.anyshchenko.diploma.roomservice.model.RoomDto;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomIndexRecord;
import mykyta.anyshchenko.diploma.roomservice.model.elasticsearch.RoomSearchRequest;
import mykyta.anyshchenko.diploma.roomservice.repository.elasticsearch.RoomIndexRepository;
import mykyta.anyshchenko.diploma.roomservice.service.RoomIndexService;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomIndexServiceImpl implements RoomIndexService {
    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;
    private final RoomIndexRepository roomIndexRepository;

    public RoomIndexServiceImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate, RoomIndexRepository roomIndexRepository) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
        this.roomIndexRepository = roomIndexRepository;
    }

    @Override
    public Mono<RoomIndexRecord> findById(Integer id) {
        return roomIndexRepository.findById(id);
    }

    @Override
    public Flux<RoomIndexRecord> findAll() {
        return roomIndexRepository.findAll();
    }

    @Override
    public Mono<Boolean> existsById(Integer id) {
        return roomIndexRepository.existsById(id);
    }

    @Override
    public Mono<Void> save(RoomIndexRecord room) {
        return roomIndexRepository.save(room).then();
    }

    @Override
    public Flux<Integer> getRoomIdsByRequest(RoomSearchRequest roomSearchRequest) {
        return getRoomIndexRecordsByRequest(roomSearchRequest)
                .map(RoomIndexRecord::getId);
    }

    @Override
    public Flux<RoomIndexRecord> getRoomIndexRecordsByRequest(RoomSearchRequest roomSearchRequest) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if(roomSearchRequest.getRoomClassId() != null){
            queryBuilder.must(QueryBuilders.termQuery("roomClassId", roomSearchRequest.getRoomClassId()));
        }

        if(roomSearchRequest.getNumberOfRooms() != null){
            queryBuilder.must(QueryBuilders.termQuery("numberOfRooms", roomSearchRequest.getNumberOfRooms()));
        }

        if(roomSearchRequest.getSleepingAreaNumber() != null){
            queryBuilder.must(QueryBuilders.termQuery("sleepingAreaNumber", roomSearchRequest.getSleepingAreaNumber()));
        }

        if(CollectionUtils.isNotEmpty(roomSearchRequest.getTagIds())) {
            for(Integer tagId : roomSearchRequest.getTagIds()) {
                queryBuilder.should(QueryBuilders.termQuery("tagIds",  tagId));
            }
            queryBuilder.minimumShouldMatch(roomSearchRequest.getTagIds().size());
        }

        return reactiveElasticsearchTemplate.find(new NativeSearchQuery(queryBuilder), RoomIndexRecord.class, "room-index");
    }
}
