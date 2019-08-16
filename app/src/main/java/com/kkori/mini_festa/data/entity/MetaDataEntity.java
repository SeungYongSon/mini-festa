package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

public class MetaDataEntity {

    @SerializedName("coverImage")
    private String coverImage;
    @SerializedName("contents")
    private String contents;

    public String getCoverImage() {
        return coverImage;
    }

    public String getContents() {
        return contents;
    }

}
