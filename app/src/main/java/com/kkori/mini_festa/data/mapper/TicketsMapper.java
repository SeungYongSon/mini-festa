package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.entity.TicketEntity;
import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketsMapper implements Mapper<List<TicketEntity>, List<Ticket>> {

    @Override
    public List<Ticket> mapFrom(List<TicketEntity> from) {
        ArrayList<Ticket> list = new ArrayList<>();

        for (TicketEntity ticket : from) {
            list.add(new Ticket(ticket.price));
        }

        return list;
    }

}
