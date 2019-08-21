package com.kkori.mini_festa.domain.entity;

import java.util.List;

public class Event {

    private int eventId;
    private String name;
    private String eventSignature;
    private String startDate;
    private String endDate;
    private List<Ticket> tickets;
    private String locationName;
    private String coverImage;
    private String contents;
    private String hostName;
    private String profileImage;
    private String ticketPriceRange;
    private String ticketBoughtCount;
    private boolean isFavorite;

    public Event(int eventId,
                 String name,
                 String eventSignature,
                 String startDate,
                 String endDate,
                 List<Ticket> tickets,
                 String locationName,
                 String coverImage,
                 String contents,
                 String hostName,
                 String profileImage,
                 boolean isFavorite) {
        this.eventId = eventId;
        this.name = name;
        this.eventSignature = eventSignature;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tickets = tickets;
        this.locationName = locationName;
        this.coverImage = coverImage;
        this.contents = contents;
        this.hostName = hostName;
        this.profileImage = profileImage;
        this.isFavorite = isFavorite;
    }

    public Event(int eventId,
                 String name,
                 String eventSignature,
                 String startDate,
                 String endDate,
                 String ticketPriceRange,
                 String ticketBoughtCount,
                 String locationName,
                 String coverImage,
                 String contents,
                 String hostName,
                 String profileImage,
                 boolean isFavorite) {
        this.eventId = eventId;
        this.name = name;
        this.eventSignature = eventSignature;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketPriceRange = ticketPriceRange;
        this.ticketBoughtCount = ticketBoughtCount;
        this.locationName = locationName;
        this.coverImage = coverImage;
        this.contents = contents;
        this.hostName = hostName;
        this.profileImage = profileImage;
        this.isFavorite = isFavorite;
    }

    public String getTicketPriceRange() {
        return ticketPriceRange;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTicketPriceRange(String ticketPriceRange) {
        this.ticketPriceRange = ticketPriceRange;
    }

    public void setTicketBoughtCount(String ticketBoughtCount) {
        this.ticketBoughtCount = ticketBoughtCount;
    }

    public void setIsFavorite(boolean isFavorite){
        this.isFavorite = isFavorite;
    }

    public String getTicketBoughtCount() {
        return ticketBoughtCount;
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

    public String getEndDate() {
        return endDate;
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

    public String getProfileImage() {
        return profileImage;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
