package com.kkori.mini_festa.presentation.di.module;

import android.content.Context;

import androidx.room.Room;

import com.kkori.mini_festa.data.database.EventDataBase;
import com.kkori.mini_festa.data.database.dao.EventDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalModule {

    @Provides
    @Singleton
    EventDataBase provideEventDatabase(Context context) {
        return Room.databaseBuilder(context, EventDataBase.class, "event.db").build();
    }

    @Provides
    @Singleton
    EventDao provideEventDao(EventDataBase eventDataBase) {
        return eventDataBase.eventDao();
    }

}
