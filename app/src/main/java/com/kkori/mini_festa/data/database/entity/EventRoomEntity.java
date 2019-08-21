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
    private String endDate;
    private String ticketPriceRange;
    private String ticketBoughtCount;
    private String locationName;
    private String coverImage;
    private String contents;
    private String hostName;
    private String profileImage;
    private boolean isFavorite;

    public EventRoomEntity(int eventId,
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

    public String getProfileImage() {
        return profileImage;
    }

    public String getTicketBoughtCount() {
        return ticketBoughtCount;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

}
