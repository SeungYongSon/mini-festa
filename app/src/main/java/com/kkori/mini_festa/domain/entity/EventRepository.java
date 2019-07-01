package com.kkori.mini_festa.domain.entity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

interface EventRepository {

    Flowable<List<Event>> getEventList(int page, int pageSize);

    Flowable<List<Event>> getLocalEventList();

    Completable saveLocalEvent(Event event);

}
