package com.kkori.mini_festa.presentation.mapper;

import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.Ticket;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventModelMapper implements Mapper<Event, EventModel> {

    @Override
    public EventModel mapFrom(Event from) {
        return new EventModel(
                from.getEventId(),
                from.getName(),
                from.getEventSignature(),
                from.getStartDate(),
                createPriceRange(from.getTickets()),
                from.getLocationName(),
                from.getCoverImage(),
                from.getHostName());
    }

    private String createPriceRange(List<Ticket> tickets) {
        if (tickets.isEmpty()) return "외부 이벤트";
        else {
            ArrayList<Integer> prices = new ArrayList<>();

            for (Ticket ticket : tickets) {
                prices.add(ticket.getPrice());
            }

            int maxPrice = Collections.max(prices);
            int minPrice = Collections.min(prices);

            DecimalFormat formatter = new DecimalFormat("###,###");

            if (maxPrice == 0 && minPrice == 0) return "무료";
            else if (maxPrice == minPrice) return '\\' + formatter.format(maxPrice);
            return '\\' + formatter.format(minPrice) + " ~ " + '\\' + formatter.format(maxPrice);
        }
    }

}
