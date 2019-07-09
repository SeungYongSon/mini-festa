package com.kkori.mini_festa.domain.entity;

import java.util.List;

import io.reactivex.Flowable;

public interface Service {

    Flowable<List<Event>> getRemoteEventList(int page, int pageSize);

    Flowable<List<Event>> getLocalEventList();

}
