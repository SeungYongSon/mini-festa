package com.kkori.mini_festa.data.database.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class EventRoomEntity {

    @PrimaryKey
    private int eventId;
    private String name;
    private String eventSignature;
    private String startDate;
    private String endDate;
    private List<TicketRoomEntity> tickets;
    @Embedded
    private LocationRoomEntity location;
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
                           List<TicketRoomEntity> tickets,
                           LocationRoomEntity location,
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
        this.location = location;
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

    public List<TicketRoomEntity> getTickets() {
        return tickets;
    }

    public LocationRoomEntity getLocation() {
        return location;
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

    public boolean isFavorite() {
        return isFavorite;
    }

}
