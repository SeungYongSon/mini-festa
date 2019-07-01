package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.entity.EventEntity;
import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Event;

public class EventMapper implements Mapper<EventEntity, Event> {

    private TicketsMapper ticketsMapper = new TicketsMapper();

    @Override
    public Event mapFrom(EventEntity from) {
        return new Event(
                from.eventId,
                from.name,
                from.eventSignature,
                from.startDate,
                ticketsMapper.mapFrom(from.tickets),
                from.location.name,
                from.metaData.coverImage,
                from.hostOrganization.name);
    }

}
