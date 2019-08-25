package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.database.entity.TicketRoomEntity;
import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketsRoomMapper implements Mapper<List<Ticket>, List<TicketRoomEntity>> {

    @Override
    public List<TicketRoomEntity> mapFrom(List<Ticket> from) {
        ArrayList<TicketRoomEntity> list = new ArrayList<>();

        for (Ticket ticket : from) {
            list.add(new TicketRoomEntity(
                    ticket.getName(),
                    ticket.getDescription(),
                    ticket.getPrice(),
                    ticket.getCount(),
                    ticket.getQuantity(),
                    ticket.getLimitPerUser(),
                    ticket.getSaleStartDate(),
                    ticket.getSaleEndDate(),
                    ticket.getRefundDueDate()
            ));
        }

        return list;
    }
}
