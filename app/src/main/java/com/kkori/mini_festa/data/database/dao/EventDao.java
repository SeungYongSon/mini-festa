package com.kkori.mini_festa.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface EventDao {

    @Query("SELECT * FROM eventroomentity")
    Flowable<List<EventRoomEntity>> getEventList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveEvent(List<EventRoomEntity> event);

}
