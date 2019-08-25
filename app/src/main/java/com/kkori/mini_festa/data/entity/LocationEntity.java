package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

public class LocationEntity {

    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("description")
    private String description;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }
}
