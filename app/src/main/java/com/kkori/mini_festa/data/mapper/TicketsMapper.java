package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.database.entity.TicketRoomEntity;
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
            list.add(new Ticket(
                    ticket.getName(),
                    ticket.getDescription(),
                    ticket.getPrice(),
                    ticket.getCount(),
                    ticket.getQuantity(),
                    ticket.getLimitPerUser(),
                    ticket.getSaleStartDate(),
                    ticket.getSaleEndDate(),
                    ticket.getRefundDueDate()));
        }

        return list;
    }

    List<Ticket> roomMapFrom(List<TicketRoomEntity> from) {
        ArrayList<Ticket> list = new ArrayList<>();

        for (TicketRoomEntity ticket : from) {
            list.add(new Ticket(
                    ticket.getTicketName(),
                    ticket.getDescription(),
                    ticket.getPrice(),
                    ticket.getCount(),
                    ticket.getQuantity(),
                    ticket.getLimitPerUser(),
                    ticket.getSaleStartDate(),
                    ticket.getSaleEndDate(),
                    ticket.getRefundDueDate()));
        }

        return list;
    }

}
