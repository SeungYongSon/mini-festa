package com.kkori.mini_festa.presentation.mapper;

import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.Ticket;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class EventModelMapper implements Mapper<Event, EventModel> {

    private static final String PRICE_FORMAT = "￦ %s ~ ￦ %s";
    private static final String UK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String KR_DATE_FORMAT = "yyyy년 MM월 dd일 hh:mm";

    private TicketModelsMapper ticketModelsMapper;
    private LocationModelMapper locationModelMapper;

    public EventModelMapper(TicketModelsMapper ticketModelsMapper, LocationModelMapper locationModelMapper) {
        this.ticketModelsMapper = ticketModelsMapper;
        this.locationModelMapper = locationModelMapper;
    }

    @Override
    public EventModel mapFrom(Event from) {
        return new EventModel(
                from.getEventId(),
                from.getName(),
                from.getEventSignature(),
                createKoreaData(from.getStartDate()),
                createKoreaData(from.getEndDate()),
                ticketModelsMapper.mapFrom(from.getTickets()),
                createPriceRange(from.getTickets()),
                countBoughtTicket(from.getTickets()),
                locationModelMapper.mapFrom(from.getLocation()),
                from.getCoverImage(),
                from.getContents(),
                from.getHostName(),
                from.getProfileImage(),
                from.isFavorite());
    }

    private String createKoreaData(String ukTime) {
        SimpleDateFormat transUK = new SimpleDateFormat(UK_DATE_FORMAT, Locale.UK);
        SimpleDateFormat transKR = new SimpleDateFormat(KR_DATE_FORMAT, Locale.KOREA);

        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(transUK.parse(ukTime));
            cal.add(Calendar.HOUR, 9);
            return transKR.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return ukTime;
        }
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
            else if (minPrice == 0) return "무료 ~ " + '￦' + formatter.format(maxPrice);
            else if (maxPrice == minPrice) return '￦' + formatter.format(maxPrice);
            return String.format(PRICE_FORMAT, formatter.format(minPrice), formatter.format(maxPrice));
        }
    }

    private String countBoughtTicket(List<Ticket> tickets) {
        if (tickets.isEmpty()) return "외부 이벤트";
        else {
            int count = 0;

            for (Ticket ticket : tickets) {
                count += ticket.getCount();
            }

            return count + "명";
        }
    }

}
