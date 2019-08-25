package com.kkori.mini_festa.domain.entity.event;

import com.kkori.mini_festa.domain.entity.Event;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class EventServiceImp implements EventService {

    private EventRepository eventRepository;

    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Flowable<List<Event>> getRemoteEventList(int page, int pageSize) {
        return eventRepository.getRemoteEventList(page, pageSize)
                .doOnNext(eventList -> {
                    for (Event event : eventList) {
                        updateEvent(event);
                    }
                });
    }

    @Override
    public Single<List<Event>> getLocalEventList() {
        return eventRepository.getLocalEventList();
    }

    @Override
    public Single<List<Event>> getFavoriteEventList() {
        return eventRepository.getFavoriteEventList();
    }

    @Override
    public Maybe<Event> getEventThroughId(int id) {
        return eventRepository.selectEvent(id);
    }

    @Override
    public Completable setFavoriteEvent(int id, boolean isFavorite) {
        return eventRepository.selectEvent(id).flatMapCompletable(event -> {
            event.setIsFavorite(isFavorite);
            return eventRepository.updateLocalEvent(event);
        });
    }

    private void updateEvent(Event event) {
        eventRepository.selectEvent(event.getEventId())
                .map(favoriteEvent -> {
                    if (favoriteEvent.isFavorite()) {
                        event.setIsFavorite(true);
                    } else {
                        event.setIsFavorite(false);
                    }

                    eventRepository.updateLocalEvent(event).subscribe();

                    return favoriteEvent;
                }).doOnComplete(() -> eventRepository.saveLocalEvent(event).subscribe()).subscribe();
    }

}
