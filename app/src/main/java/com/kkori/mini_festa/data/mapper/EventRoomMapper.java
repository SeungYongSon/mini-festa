package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;
import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Event;

public class EventRoomMapper implements Mapper<Event, EventRoomEntity> {

    private TicketsRoomMapper ticketsRoomMapper;
    private LocationRoomMapper locationRoomMapper;

    public EventRoomMapper(TicketsRoomMapper ticketsRoomMapper, LocationRoomMapper locationRoomMapper) {
        this.ticketsRoomMapper = ticketsRoomMapper;
        this.locationRoomMapper = locationRoomMapper;
    }

    @Override
    public EventRoomEntity mapFrom(Event from) {
        return new EventRoomEntity(
                from.getEventId(),
                from.getName(),
                from.getEventSignature(),
                from.getStartDate(),
                from.getEndDate(),
                ticketsRoomMapper.mapFrom(from.getTickets()),
                locationRoomMapper.mapFrom(from.getLocation()),
                from.getCoverImage(),
                from.getContents(),
                from.getHostName(),
                from.getProfileImage(),
                from.isFavorite());
    }

}
