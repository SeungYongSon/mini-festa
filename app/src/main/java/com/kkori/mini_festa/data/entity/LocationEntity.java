package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

public class LocationEntity {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
