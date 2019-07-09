package com.kkori.mini_festa.data.datasource;

import com.kkori.mini_festa.data.dto.EventListDTO;

import io.reactivex.Flowable;

public interface EventRemoteDataSource {

    Flowable<EventListDTO> getEventList(int page, int pageSize);

}
