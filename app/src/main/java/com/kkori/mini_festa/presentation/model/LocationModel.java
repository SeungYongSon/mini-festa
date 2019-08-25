package com.kkori.mini_festa.presentation.model;

public class LocationModel {

    private String name;
    private String description;
    private String address;

    public LocationModel(String name, String description, String address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }
}
