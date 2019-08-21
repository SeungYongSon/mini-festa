package com.kkori.mini_festa.data.repository;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;
import com.kkori.mini_festa.data.datasource.EventLocalDataSource;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSource;
import com.kkori.mini_festa.data.entity.EventEntity;
import com.kkori.mini_festa.data.mapper.EventMapper;
import com.kkori.mini_festa.data.mapper.EventRoomMapper;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.event.EventRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class EventRepositoryImp implements EventRepository {

    private EventRemoteDataSource eventRemoteDataSource;
    private EventLocalDataSource eventLocalDataSource;
    private EventMapper eventMapper;
    private EventRoomMapper eventRoomMapper;

    public EventRepositoryImp(EventRemoteDataSource eventRemoteDataSource,
                              EventLocalDataSource eventLocalDataSource,
                              EventMapper eventMapper,
                              EventRoomMapper eventRoomMapper) {
        this.eventRemoteDataSource = eventRemoteDataSource;
        this.eventLocalDataSource = eventLocalDataSource;
        this.eventMapper = eventMapper;
        this.eventRoomMapper = eventRoomMapper;
    }

    @Override
    public Flowable<List<Event>> getRemoteEventList(int page, int pageSize) {
        return eventRemoteDataSource.getEventList(page, pageSize)
                .map(eventListDTO -> {
                    ArrayList<Event> list = new ArrayList<>();

                    for (EventEntity event : eventListDTO.rows) {
                        list.add(eventMapper.mapFrom(event));
                    }

                    return list;
                });
    }

    @Override
    public Single<List<Event>> getLocalEventList() {
        return eventLocalDataSource.getLocalEventList()
                .map(eventRoomEntities -> {
                    ArrayList<Event> list = new ArrayList<>();

                    for (EventRoomEntity event : eventRoomEntities) {
                        list.add(eventMapper.mapFrom(event));
                    }

                    return list;
                });
    }

    @Override
    public Completable saveLocalEvent(Event event) {
        return eventLocalDataSource.saveLocalEvent(eventRoomMapper.mapFrom(event));
    }

    @Override
    public Single<List<Event>> getFavoriteEventList() {
        return eventLocalDataSource.getFavoriteEventList()
                .map(eventRoomEntities -> {
                    ArrayList<Event> list = new ArrayList<>();

                    for (EventRoomEntity event : eventRoomEntities) {
                        list.add(eventMapper.mapFrom(event));
                    }

                    return list;
                });
    }

    @Override
    public Maybe<Event> selectEvent(int id) {
        return eventLocalDataSource.selectFavoriteEvent(id)
                .map(eventRoomEntity -> eventMapper.mapFrom(eventRoomEntity));
    }

}
