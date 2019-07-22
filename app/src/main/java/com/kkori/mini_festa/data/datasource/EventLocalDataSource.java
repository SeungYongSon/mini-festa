package com.kkori.mini_festa.data.datasource;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface EventLocalDataSource {

    Flowable<List<EventRoomEntity>> getLocalEventList();
    Completable saveLocalEvent(List<EventRoomEntity> event);

}
