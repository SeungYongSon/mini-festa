package com.kkori.mini_festa.data.datasource;

import com.kkori.mini_festa.data.entity.EventEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class EventLocalDataSourceImp implements EventLocalDataSource {

    @Override
    public Flowable<List<EventEntity>> getLocalEventList() {
        return null;
    }

    @Override
    public Completable saveLocalEvent(EventEntity event) {
        return null;
    }

}
