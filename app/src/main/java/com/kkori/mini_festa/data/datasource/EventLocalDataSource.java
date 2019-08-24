package com.kkori.mini_festa.data.datasource;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface EventLocalDataSource {

    Single<List<EventRoomEntity>> getLocalEventList();

    Completable saveLocalEvent(EventRoomEntity event);

    Completable updateLocalEvent(EventRoomEntity event);

    Single<List<EventRoomEntity>> getFavoriteEventList();

    Maybe<EventRoomEntity> selectFavoriteEvent(int id);

}
