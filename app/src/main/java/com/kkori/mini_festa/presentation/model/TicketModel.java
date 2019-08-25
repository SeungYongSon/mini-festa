package com.kkori.mini_festa.presentation.model;

public class TicketModel {

    private String name;
    private String description;
    private String price;
    private String count;
    private String quantity;
    private String limitPerUser;
    private String saleDate;
    private boolean isSale;

    public TicketModel(String name,
                       String description,
                       String price,
                       String count,
                       String quantity,
                       String limitPerUser,
                       String saleDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
        this.quantity = quantity;
        this.limitPerUser = limitPerUser;
        this.saleDate = saleDate;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getCount() {
        return count;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getLimitPerUser() {
        return limitPerUser;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean isSale) {
        this.isSale = isSale;
    }
}
