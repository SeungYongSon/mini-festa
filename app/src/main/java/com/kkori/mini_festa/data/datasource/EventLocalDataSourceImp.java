package com.kkori.mini_festa.data.datasource;

import com.kkori.mini_festa.data.database.dao.EventDao;
import com.kkori.mini_festa.data.database.entity.EventRoomEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EventLocalDataSourceImp implements EventLocalDataSource {

    private EventDao eventDao;

    public EventLocalDataSourceImp(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Single<List<EventRoomEntity>> getLocalEventList() {
        return eventDao.getEventList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable saveLocalEvent(EventRoomEntity eventRoomEntity) {
        return eventDao.saveEvent(eventRoomEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<EventRoomEntity>> getFavoriteEventList() {
        return eventDao.getFavoriteEventList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Maybe<EventRoomEntity> selectFavoriteEvent(int id) {
        return eventDao.selectEventById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
