package com.kkori.mini_festa.data.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EventRoomEntity {

    @PrimaryKey
    private int eventId;
    private String name;
    private String eventSignature;
    private String startDate;
    private String ticketPriceRange;
    private String locationName;
    private String coverImage;
    private String contents;
    private String hostName;

    public EventRoomEntity(int eventId,
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

    public String getTicketPriceRange() {
        return ticketPriceRange;
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

}
