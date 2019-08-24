package com.kkori.mini_festa.presentation.mapper;

import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Ticket;
import com.kkori.mini_festa.presentation.model.TicketModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TicketModelsMapper implements Mapper<List<Ticket>, List<TicketModel>> {

    private static final String UK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String DAY_FORMAT = "dd";

    private SimpleDateFormat transUK = new SimpleDateFormat(UK_DATE_FORMAT, Locale.UK);
    private SimpleDateFormat transDay = new SimpleDateFormat(DAY_FORMAT, Locale.KOREA);

    private boolean isSale = true;

    @Override
    public List<TicketModel> mapFrom(List<Ticket> from) {
        ArrayList<TicketModel> list = new ArrayList<>();

        for (Ticket ticket : from) {
            TicketModel ticketModel = new TicketModel(
                    ticket.getName(),
                    ticket.getDescription(),
                    manufacturePrice(ticket.getPrice()),
                    manufactureSaleCount(ticket.getCount()),
                    manufactureQuantity(ticket.getQuantity(), ticket.getCount()),
                    manufactureLimitPerUser(ticket.getLimitPerUser()),
                    manufactureSaleDate(ticket.getSaleStartDate(), ticket.getSaleEndDate()));

            ticketModel.setSale(isSale);

            list.add(ticketModel);

            isSale = true;
        }

        return list;
    }

    private String manufacturePrice(int price) {
        if (price == 0) return "무료";
        else {
            DecimalFormat formatter = new DecimalFormat("###,###");
            return '￦' + formatter.format(price);
        }
    }

    private String manufactureSaleCount(int count) {
        return count + "개 판매";
    }

    private String manufactureQuantity(int quantity, int count) {
        if (quantity - count == 0) isSale = false;
        return quantity - count + "개 남음";
    }

    private String manufactureLimitPerUser(int limitPerUser) {
        return "1인당 " + limitPerUser + "개 구입가능";
    }

    private String manufactureSaleDate(String saleStartDate, String saleEndDate) {
        long currentTime = System.currentTimeMillis();

        try {
            Date startDate = transUK.parse(saleStartDate);
            Date endDate = transUK.parse(saleEndDate);

            long startTime = startDate.getTime();
            long endTime = endDate.getTime();

            if (currentTime > startTime && currentTime < endTime) {
                long remainingTime = endTime - currentTime;
                Date remainDate = new Date(remainingTime);

                return transDay.format(remainDate) + "일 후에 판매마감";
            } else {
                if (currentTime < startTime) {
                    long remainingTime = currentTime - startTime;
                    Date remainDate = new Date(remainingTime);

                    isSale = false;

                    return transDay.format(remainDate) + "일 후에 판매시작";
                }

                if (currentTime > endTime) {
                    isSale = false;
                    return "판매기간이 종료되었습니다";
                }
                isSale = false;
            }
        } catch (ParseException e) {
            isSale = false;
            e.printStackTrace();
        }

        return "판매기간을 알 수 없습니다";
    }

}
