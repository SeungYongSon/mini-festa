package com.kkori.mini_festa.domain.entity.event;

import com.kkori.mini_festa.domain.entity.Event;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface EventService {

    Flowable<List<Event>> getRemoteEventList(int page, int pageSize);

    Single<List<Event>> getLocalEventList();

    Single<List<Event>> getFavoriteEventList();

    Maybe<Event> getEventThroughId(int id);

    Completable setFavoriteEvent(int id, boolean isFavorite);

}
