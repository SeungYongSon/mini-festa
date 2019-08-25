package com.kkori.mini_festa.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.kkori.mini_festa.data.database.dao.EventDao;
import com.kkori.mini_festa.data.database.entity.EventRoomEntity;

@Database(entities = {EventRoomEntity.class}, version = 1, exportSchema = false)
@TypeConverters({TicketRoomEntityListConverter.class})
public abstract class EventDataBase extends RoomDatabase {

    public abstract EventDao eventDao();

}
