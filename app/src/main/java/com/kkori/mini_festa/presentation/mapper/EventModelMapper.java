package com.kkori.mini_festa.presentation.mapper;

import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.Ticket;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventModelMapper implements Mapper<Event, EventModel> {

    private TicketModelsMapper ticketModelsMapper;
    private LocationModelMapper locationModelMapper;
    private EventFormat eventFormat;

    public EventModelMapper(TicketModelsMapper ticketModelsMapper,
                            LocationModelMapper locationModelMapper,
                            EventFormat eventFormat) {
        this.ticketModelsMapper = ticketModelsMapper;
        this.locationModelMapper = locationModelMapper;
        this.eventFormat = eventFormat;
    }

    @Override
    public EventModel mapFrom(Event from) {
        return new EventModel(
                from.getEventId(),
                from.getName(),
                from.getEventSignature(),
                manufactureEventStartDate(from.getStartDate()),
                manufactureEndDate(from.getStartDate(), from.getEndDate()),
                ticketModelsMapper.mapFrom(from.getTickets()),
                manufacturePriceRange(from.getTickets()),
                countBoughtTicket(from.getTickets()),
                locationModelMapper.mapFrom(from.getLocation()),
                from.getCoverImage(),
                from.getContents(),
                from.getHostName(),
                from.getProfileImage(),
                from.isFavorite());
    }

    private String manufactureEventStartDate(String time) {
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(eventFormat.getTransUKFormat().parse(time));
            cal.add(Calendar.HOUR, 9);
            return eventFormat.getTransStartDateEventFormat().format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return time;
        }
    }

    private String manufactureEndDate(String startEventDate, String endEventDate) {
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        try {
            Date startDate = eventFormat.getTransUKFormat().parse(startEventDate);
            Date endDate = eventFormat.getTransUKFormat().parse(endEventDate);

            calendarStart.setTime(startDate);
            calendarEnd.setTime(endDate);

            calendarStart.add(Calendar.HOUR, 9);
            calendarEnd.add(Calendar.HOUR, 9);

            long startTime = calendarStart.getTime().getTime();
            long endTime = calendarEnd.getTime().getTime();

            long eventTime = endTime - startTime;

            if (eventTime > eventFormat.getYear()) {
                return eventFormat.getTransDifferentYearEventFormat().format(calendarStart.getTime())
                        + "\n- " + eventFormat.getTransDifferentYearEventFormat().format(calendarEnd.getTime());
            } else if (eventTime > eventFormat.getDay()) {
                return eventFormat.getTransDifferentMonthAndDayEventFormat().format(calendarStart.getTime())
                        + "\n- " + eventFormat.getTransDifferentMonthAndDayEndTimeEventFormat().format(calendarEnd.getTime());
            } else {
                String startEventTime = eventFormat.getTransSameDayEventFormat().format(calendarStart.getTime());

                return startEventTime.replace(",", "\n")
                        + " - " + eventFormat.getTransSameDayEndTimeEventFormat().format(calendarEnd.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return endEventDate;
        }
    }

    private String manufacturePriceRange(List<Ticket> tickets) {
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
            return String.format(eventFormat.getPriceFormat(), formatter.format(minPrice), formatter.format(maxPrice));
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
