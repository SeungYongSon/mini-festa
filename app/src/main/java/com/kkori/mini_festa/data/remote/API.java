package com.kkori.mini_festa.data.remote;

import com.kkori.mini_festa.data.dto.EventListDTO;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("events")
    Flowable<EventListDTO> getEventList(@Query("page") int page,
                                        @Query("pageSize") int pageSize,
                                        @Query("order") String order);

}
