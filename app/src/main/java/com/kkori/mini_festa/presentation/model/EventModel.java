package com.kkori.mini_festa.presentation.model;

import java.util.List;

public class EventModel {

    private int eventId;
    private String name;
    private String eventSignature;
    private String startDate;
    private String eventProgressTime;
    private List<TicketModel> tickets;
    private String ticketPriceRange;
    private String ticketBoughtCount;
    private LocationModel location;
    private String coverImage;
    private String contents;
    private String hostName;
    private String profileImage;
    private boolean isFavorite;

    public EventModel(int eventId,
                      String name,
                      String eventSignature,
                      String startDate,
                      String eventProgressTime,
                      List<TicketModel> tickets,
                      String ticketPriceRange,
                      String ticketBoughtCount,
                      LocationModel location,
                      String coverImage,
                      String contents,
                      String hostName,
                      String profileImage,
                      boolean isFavorite) {
        this.eventId = eventId;
        this.name = name;
        this.eventSignature = eventSignature;
        this.startDate = startDate;
        this.eventProgressTime = eventProgressTime;
        this.tickets = tickets;
        this.ticketPriceRange = ticketPriceRange;
        this.ticketBoughtCount = ticketBoughtCount;
        this.location = location;
        this.coverImage = coverImage;
        this.contents = contents;
        this.hostName = hostName;
        this.profileImage = profileImage;
        this.isFavorite = isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
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

    public String getEventProgressTime() {
        return eventProgressTime;
    }

    public List<TicketModel> getTickets() {
        return tickets;
    }

    public LocationModel getLocation() {
        return location;
    }

    public String getTicketPriceRange() {
        return ticketPriceRange;
    }

    public String getTicketBoughtCount() {
        return ticketBoughtCount;
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

    public int getEventId() {
        return eventId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

}
