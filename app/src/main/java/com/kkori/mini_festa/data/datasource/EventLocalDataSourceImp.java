package com.kkori.mini_festa.data.datasource;

import android.util.Log;

import com.kkori.mini_festa.data.database.dao.EventDao;
import com.kkori.mini_festa.data.database.entity.EventRoomEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class EventLocalDataSourceImp implements EventLocalDataSource {

    private EventDao eventDao;

    public EventLocalDataSourceImp(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Flowable<List<EventRoomEntity>> getLocalEventList() {
        return eventDao.getEventList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveLocalEvent(List<EventRoomEntity> eventRoomEntities) {
        eventDao.saveEvent(eventRoomEntities)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.e("Save Event", "Complete!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Save Event", e.getMessage());
                    }
                });
    }

}
