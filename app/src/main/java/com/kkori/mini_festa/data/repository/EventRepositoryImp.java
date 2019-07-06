package com.kkori.mini_festa.data.repository;

import com.kkori.mini_festa.data.datasource.EventRemoteDataSource;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSourceImp;
import com.kkori.mini_festa.data.dto.EventListDTO;
import com.kkori.mini_festa.data.entity.EventEntity;
import com.kkori.mini_festa.data.mapper.EventMapper;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.EventRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class EventRepositoryImp implements EventRepository {

    private EventRemoteDataSource eventRemoteDataSource;
    private EventMapper eventMapper;

    public EventRepositoryImp(EventRemoteDataSource eventRemoteDataSource, EventMapper eventMapper) {
        this.eventRemoteDataSource = eventRemoteDataSource;
        this.eventMapper = eventMapper;
    }

    @Override
    public Flowable<List<Event>> getEventList(int page, int pageSize) {
        return eventRemoteDataSource.getEventList(page, pageSize)
                .map(new Function<EventListDTO, List<Event>>() {
                    @Override
                    public List<Event> apply(EventListDTO eventListDTO) {
                        ArrayList<Event> list = new ArrayList<>();

                        for (EventEntity event : eventListDTO.rows) {
                            list.add(eventMapper.mapFrom(event));
                        }

                        return list;
                    }
                });
    }

    @Override
    public Flowable<List<Event>> getLocalEventList() {
        return null;
    }

    @Override
    public Completable saveLocalEvent(Event event) {
        return null;
    }

}
