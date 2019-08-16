package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;
import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Event;

import java.util.ArrayList;
import java.util.List;

public class EventRoomMapper implements Mapper<List<Event>, List<EventRoomEntity>> {

    @Override
    public List<EventRoomEntity> mapFrom(List<Event> from) {
        ArrayList<EventRoomEntity> roomEntities = new ArrayList<>();

        for (Event event : from) {
            roomEntities.add(new EventRoomEntity(
                    event.getEventId(),
                    event.getName(),
                    event.getEventSignature(),
                    event.getStartDate(),
                    event.getTicketPriceRange(),
                    event.getLocationName(),
                    event.getCoverImage(),
                    event.getContents(),
                    event.getName()));
        }

        return roomEntities;
    }

}
