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
    private String contents;
    private String hostName;
    private String ticketPriceRange;

    public Event(int eventId,
                 String name,
                 String eventSignature,
                 String startDate,
                 List<Ticket> tickets,
                 String locationName,
                 String coverImage,
                 String contents,
                 String hostName) {
        this.eventId = eventId;
        this.name = name;
        this.eventSignature = eventSignature;
        this.startDate = startDate;
        this.tickets = tickets;
        this.locationName = locationName;
        this.coverImage = coverImage;
        this.contents = contents;
        this.hostName = hostName;
    }

    public Event(int eventId,
                 String name,
                 String eventSignature,
                 String startDate,
                 String ticketPriceRange,
                 String locationName,
                 String coverImage,
                 String contents,
                 String hostName) {
        this.eventId = eventId;
        this.name = name;
        this.eventSignature = eventSignature;
        this.startDate = startDate;
        this.ticketPriceRange = ticketPriceRange;
        this.locationName = locationName;
        this.coverImage = coverImage;
        this.contents = contents;
        this.hostName = hostName;
    }

    public String getTicketPriceRange() {
        return ticketPriceRange;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTicketPriceRange(String ticketPriceRange) {
        this.ticketPriceRange = ticketPriceRange;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getEventSignature() {
        return eventSignature;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getContents() {
        return contents;
    }

    public String getHostName() {
        return hostName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

}
