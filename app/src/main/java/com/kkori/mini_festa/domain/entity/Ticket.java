package com.kkori.mini_festa.domain.entity;

public class Ticket {

    private int price;
    private int count;

    public Ticket(int price, int count) {
        this.price = price;
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
