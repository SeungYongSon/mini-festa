package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

public class TicketEntity {

    @SerializedName("registable")
    private Boolean registable;
    @SerializedName("ticketId")
    private int ticketId;
    @SerializedName("eventId")
    private int eventId;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("type")
    private String type;
    @SerializedName("price")
    private int price;
    @SerializedName("currency")
    private String currency;
    @SerializedName("count")
    private int count;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("limitPerUser")
    private int limitPerUser;
    @SerializedName("saleStartDate")
    private String saleStartDate;
    @SerializedName("saleEndDate")
    private String saleEndDate;
    @SerializedName("refundDueDate")
    private String refundDueDate;
    @SerializedName("hideRemains")
    private boolean hideRemains;
    @SerializedName("useSurvey")
    private boolean useSurvey;
    @SerializedName("surveyNotice")
    private String surveyNotice;

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
