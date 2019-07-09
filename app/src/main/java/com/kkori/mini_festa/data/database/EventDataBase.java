package com.kkori.mini_festa.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kkori.mini_festa.data.database.dao.EventDao;
import com.kkori.mini_festa.data.database.entity.EventRoomEntity;

@Database(entities = {EventRoomEntity.class}, version = 1)
public abstract class EventDataBase extends RoomDatabase {

    public abstract EventDao eventDao();

}
