package com.kkori.mini_festa.domain.entity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class ServiceImp implements Service {

    private EventRepository eventRepository;

    ServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Flowable<List<Event>> getEventList(int page, int pageSize) {
        return eventRepository.getEventList(page, pageSize);
    }

    @Override
    public Flowable<List<Event>> getLocalEventList() {
        return eventRepository.getLocalEventList();
    }

    @Override
    public Completable saveLocalEvent(Event event) {
        return eventRepository.saveLocalEvent(event);
    }

}
