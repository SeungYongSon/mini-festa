package com.kkori.mini_festa.data.database.entity;

public class TicketRoomEntity {
    private String ticketName;
    private String description;
    private int price;
    private int count;
    private int quantity;
    private int limitPerUser;
    private String saleStartDate;
    private String saleEndDate;
    private String refundDueDate;

    public TicketRoomEntity(String ticketName,
                            String description,
                            int price,
                            int count,
                            int quantity,
                            int limitPerUser,
                            String saleStartDate,
                            String saleEndDate,
                            String refundDueDate) {
        this.ticketName = ticketName;
        this.description = description;
        this.price = price;
        this.count = count;
        this.quantity = quantity;
        this.limitPerUser = limitPerUser;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
        this.refundDueDate = refundDueDate;
    }

    public String getTicketName() {
        return ticketName;
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
