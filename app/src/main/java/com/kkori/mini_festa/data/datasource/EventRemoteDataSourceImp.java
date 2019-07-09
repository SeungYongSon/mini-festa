package com.kkori.mini_festa.data.datasource;

import com.kkori.mini_festa.data.API;
import com.kkori.mini_festa.data.dto.EventListDTO;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EventRemoteDataSourceImp implements EventRemoteDataSource {

    private API api;

    public EventRemoteDataSourceImp(API api) {
        this.api = api;
    }

    @Override
    public Flowable<EventListDTO> getEventList(int page, int pageSize) {
        return api.getEventList(page, pageSize, "startDate")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
