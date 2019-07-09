package com.kkori.mini_festa.domain.entity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class ServiceImp implements Service {

    private EventRepository eventRepository;

    public ServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Flowable<List<Event>> getRemoteEventList(int page, int pageSize) {
        return eventRepository.getRemoteEventList(page, pageSize)
                .map(new Function<List<Event>, List<Event>>() {
                    @Override
                    public List<Event> apply(List<Event> eventList) {
                        for (Event event : eventList) {
                            event.setTicketPriceRange(createPriceRange(event.getTickets()));
                            event.setStartDate(createKoreaData(event.getStartDate()));
                        }

                        eventRepository.saveLocalEvent(eventList);

                        return eventList;
                    }
                });
    }

    @Override
    public Flowable<List<Event>> getLocalEventList() {
        return eventRepository.getLocalEventList();
    }

    private String createKoreaData(String ukTime) {
        SimpleDateFormat transUK = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
        SimpleDateFormat transKR = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm", Locale.KOREA);

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
            else if (maxPrice == minPrice) return '￦' + formatter.format(maxPrice);
            return '￦' + formatter.format(minPrice) + " ~ " + '￦' + formatter.format(maxPrice);
        }
    }

}
