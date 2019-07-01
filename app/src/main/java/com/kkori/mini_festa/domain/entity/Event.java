package com.kkori.mini_festa.domain.entity;

import java.util.List;

public class Event {

    private int eventId;
    private String name;
    private String eventSignature;
    private String startDate;
    private List<Ticket> tickets;
    private String locationName;
    private String coverImage;
    private String hostName;

    public Event(int eventId,
                 String name,
                 String eventSignature,
                 String startDate,
                 List<Ticket> tickets,
                 String locationName,
                 String coverImage,
                 String hostName) {
        this.eventId = eventId;
        this.name = name;
        this.eventSignature = eventSignature;
        this.startDate = startDate;
        this.tickets = tickets;
        this.locationName = locationName;
        this.coverImage = coverImage;
        this.hostName = hostName;
    }

}
