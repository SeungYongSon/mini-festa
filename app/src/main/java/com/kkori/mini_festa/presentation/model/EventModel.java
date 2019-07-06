package com.kkori.mini_festa.presentation.model;

public class EventModel {

    private int eventId;
    private String name;
    private String eventSignature;
    private String startDate;
    private String ticketPriceRange;
    private String locationName;
    private String coverImage;
    private String hostName;

    public EventModel(int eventId,
                      String name,
                      String eventSignature,
                      String startDate,
                      String ticketPriceRange,
                      String locationName,
                      String coverImage,
                      String hostName) {
        this.eventId = eventId;
        this.name = name;
        this.eventSignature = eventSignature;
        this.startDate = startDate;
        this.ticketPriceRange = ticketPriceRange;
        this.locationName = locationName;
        this.coverImage = coverImage;
        this.hostName = hostName;
    }

}
