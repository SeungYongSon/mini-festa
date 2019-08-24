package com.kkori.mini_festa.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface EventDao {

    @Query("SELECT * FROM eventroomentity")
    Single<List<EventRoomEntity>> getEventList();

    @Query("SELECT * FROM eventroomentity where isFavorite")
    Single<List<EventRoomEntity>> getFavoriteEventList();

    @Query("SELECT * FROM eventroomentity where eventId = :id ")
    Maybe<EventRoomEntity> selectEventById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveEvent(EventRoomEntity event);

}
