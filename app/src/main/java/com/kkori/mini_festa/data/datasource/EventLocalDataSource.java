package com.kkori.mini_festa.data.datasource;

import com.kkori.mini_festa.data.entity.EventEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface EventLocalDataSource {

    Flowable<List<EventEntity>> getLocalEventList();
    Completable saveLocalEvent(EventEntity event);

}
