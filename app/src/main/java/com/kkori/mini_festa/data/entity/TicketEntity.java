package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

public class TicketEntity {

    @SerializedName("price")
    private int price;

    public int getPrice() {
        return price;
    }

}
