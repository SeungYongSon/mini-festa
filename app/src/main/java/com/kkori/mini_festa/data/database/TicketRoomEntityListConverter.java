package com.kkori.mini_festa.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kkori.mini_festa.data.database.entity.TicketRoomEntity;

import java.lang.reflect.Type;
import java.util.List;

public class TicketRoomEntityListConverter {

    @TypeConverter
    public static List<TicketRoomEntity> fromString(String value) {
        Type listType = new TypeToken<List<TicketRoomEntity>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<TicketRoomEntity> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
