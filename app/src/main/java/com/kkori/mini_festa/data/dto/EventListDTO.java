package com.kkori.mini_festa.data.dto;

import com.google.gson.annotations.SerializedName;
import com.kkori.mini_festa.data.entity.EventEntity;

import java.util.List;

public class EventListDTO {

    @SerializedName("rows")
    public List<EventEntity> rows;

}
