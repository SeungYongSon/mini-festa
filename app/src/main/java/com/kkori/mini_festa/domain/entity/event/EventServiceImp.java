package com.kkori.mini_festa.domain.entity.event;

import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.Ticket;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class EventServiceImp implements EventService {

    private static final String PRICE_FORMAT = "￦ %s ~ ￦ %s";
    private static final String UK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String KR_DATE_FORMAT = "yyyy년 MM월 dd일 hh:mm";

    private EventRepository eventRepository;

    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Flowable<List<Event>> getRemoteEventList(int page, int pageSize) {
        return eventRepository.getRemoteEventList(page, pageSize)
                .doOnNext(eventList -> {
                    for (Event event : eventList) {
                        event.setTicketPriceRange(createPriceRange(event.getTickets()));
                        event.setTicketBoughtCount(countBoughtTicket(event.getTickets()));
                        event.setStartDate(createKoreaData(event.getStartDate()));
                        event.setEndDate(createKoreaData(event.getEndDate()));

                        updateEvent(event);
                    }
                });
    }


    @Override
    public Single<List<Event>> getLocalEventList() {
        return eventRepository.getLocalEventList();
    }

    @Override
    public Single<List<Event>> getFavoriteEventList() {
        return eventRepository.getFavoriteEventList();
    }

    @Override
    public Maybe<Event> getEventThroughId(int id) {
        return eventRepository.selectEvent(id);
    }

    @Override
    public Completable setFavoriteEvent(int id, boolean isFavorite) {
        return eventRepository.selectEvent(id).flatMapCompletable(event -> {
            event.setIsFavorite(isFavorite);
            return eventRepository.saveLocalEvent(event);
        });
    }

    private void updateEvent(Event event) {
        eventRepository.selectEvent(event.getEventId())
                .map(favoriteEvent -> {
                    if (favoriteEvent.isFavorite()) {
                        event.setIsFavorite(true);
                    } else {
                        event.setIsFavorite(false);
                    }
                   return favoriteEvent;
                })
                .doOnComplete(() -> eventRepository.saveLocalEvent(event).subscribe())
                .subscribe();
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
