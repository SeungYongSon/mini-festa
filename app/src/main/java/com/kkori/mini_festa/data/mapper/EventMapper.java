package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.database.entity.EventRoomEntity;
import com.kkori.mini_festa.data.entity.EventEntity;
import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Event;

public class EventMapper implements Mapper<EventEntity, Event> {

    private TicketsMapper ticketsMapper = new TicketsMapper();

    @Override
    public Event mapFrom(EventEntity from) {
        return new Event(
                from.getEventId(),
                from.getName(),
                from.getEventSignature(),
                from.getStartDate(),
                from.getEndDate(),
                ticketsMapper.mapFrom(from.getTickets()),
                from.getLocation().getName(),
                from.getMetaData().getCoverImage(),
                from.getMetaData().getContents(),
                from.getHostOrganization().getName(),
                from.getHostOrganization().getProfileImage(),
                false);
    }

    public Event mapFrom(EventRoomEntity from) {
        return new Event(
                from.getEventId(),
                from.getName(),
                from.getEventSignature(),
                from.getStartDate(),
                from.getEndDate(),
                from.getTicketPriceRange(),
                from.getTicketBoughtCount(),
                from.getLocationName(),
                from.getCoverImage(),
                from.getContents(),
                from.getHostName(),
                from.getProfileImage(),
                from.isFavorite());
    }

}
