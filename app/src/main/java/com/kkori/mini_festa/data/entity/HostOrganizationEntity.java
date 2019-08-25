package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

public class HostOrganizationEntity {

    @SerializedName("name")
    private String name;
    @SerializedName("profileImage")
    private String profileImage;

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
