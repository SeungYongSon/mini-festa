package com.kkori.mini_festa.domain.entity.event;

import com.kkori.mini_festa.domain.entity.Event;

import java.util.List;

import io.reactivex.Flowable;

public interface EventService {

    Flowable<List<Event>> getRemoteEventList(int page, int pageSize);

    Flowable<List<Event>> getLocalEventList();

}
