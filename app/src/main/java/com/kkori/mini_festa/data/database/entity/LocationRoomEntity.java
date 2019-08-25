package com.kkori.mini_festa.data.database.entity;

public class LocationRoomEntity {
    private String locationName;
    private String description;
    private String address;

    public LocationRoomEntity(String locationName, String description, String address) {
        this.locationName = locationName;
        this.description = description;
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }
}
