package com.kkori.mini_festa.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EventModel implements Parcelable {

    private int eventId;
    private String name;
    private String eventSignature;
    private String startDate;
    private String ticketPriceRange;
    private String locationName;
    private String coverImage;
    private String contents;
    private String hostName;

    public EventModel(int eventId,
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

    @Override
    public int describeContents() {
        return 0;
    }

    private EventModel(Parcel in) {
        this.eventId = in.readInt();
        this.name = in.readString();
        this.eventSignature = in.readString();
        this.startDate = in.readString();
        this.ticketPriceRange = in.readString();
        this.locationName = in.readString();
        this.coverImage = in.readString();
        this.contents = in.readString();
        this.hostName = in.readString();
    }

    public static final Creator<EventModel> CREATOR = new Creator<EventModel>() {
        @Override
        public EventModel createFromParcel(Parcel in) {
            return new EventModel(in);
        }

        @Override
        public EventModel[] newArray(int size) {
            return new EventModel[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(eventId);
        dest.writeString(name);
        dest.writeString(eventSignature);
        dest.writeString(startDate);
        dest.writeString(ticketPriceRange);
        dest.writeString(locationName);
        dest.writeString(coverImage);
        dest.writeString(contents);
        dest.writeString(hostName);
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

    public int getEventId() {
        return eventId;
    }

}
