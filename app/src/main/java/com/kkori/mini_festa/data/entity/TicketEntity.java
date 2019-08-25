package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

public class TicketEntity {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private int price;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLimitPerUser() {
        return limitPerUser;
    }

    public String getSaleStartDate() {
        return saleStartDate;
    }

    public String getSaleEndDate() {
        return saleEndDate;
    }

    public String getRefundDueDate() {
        return refundDueDate;
    }
}
