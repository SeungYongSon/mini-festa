package com.kkori.mini_festa.data.repository;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;
import com.kkori.mini_festa.data.datasource.EventLocalDataSource;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSource;
import com.kkori.mini_festa.data.dto.EventListDTO;
import com.kkori.mini_festa.data.entity.EventEntity;
import com.kkori.mini_festa.data.mapper.EventMapper;
import com.kkori.mini_festa.data.mapper.EventRoomMapper;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.EventRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

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
        return eventLocalDataSource.getLocalEventList()
                .map(new Function<List<EventRoomEntity>, List<Event>>() {
                    @Override
                    public List<Event> apply(List<EventRoomEntity> eventRoomEntities) {
                        ArrayList<Event> list = new ArrayList<>();

                        for (EventRoomEntity event : eventRoomEntities) {
                            list.add(eventMapper.mapFrom(event));
                        }

                        return list;
                    }
                });
    }

    @Override
    public void saveLocalEvent(List<Event> events) {
        eventLocalDataSource.saveLocalEvent(eventRoomMapper.mapFrom(events));
    }

}
