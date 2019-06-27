package com.kkori.mini_festa.domain.entity;

import java.util.List;

public class Event {

    private int eventId;
    private String eventName;
    private String eventDate;
    private List<Ticket> tickets;
    private String locationName;
    private String eventImageUrl;
    private String hostName;
    private String ticketPriceRange;

    Event(int eventId,
          String eventName,
          String eventDate,
          List<Ticket> tickets,
          String locationName,
          String eventImageUrl,
          String hostName,
          String ticketPriceRange) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.tickets = tickets;
        this.locationName = locationName;
        this.eventImageUrl = eventImageUrl;
        this.hostName = hostName;
        this.ticketPriceRange = ticketPriceRange;
    }

}
