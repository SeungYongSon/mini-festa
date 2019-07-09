package com.kkori.mini_festa.presentation.mapper;

import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.presentation.model.EventModel;

public class EventModelMapper implements Mapper<Event, EventModel> {

    @Override
    public EventModel mapFrom(Event from) {
        return new EventModel(
                from.getEventId(),
                from.getName(),
                from.getEventSignature(),
                from.getStartDate(),
                from.getTicketPriceRange(),
                from.getLocationName(),
                from.getCoverImage(),
                from.getHostName());
    }

}
