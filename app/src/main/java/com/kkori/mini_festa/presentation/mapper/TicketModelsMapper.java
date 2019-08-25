package com.kkori.mini_festa.presentation.mapper;

import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Ticket;
import com.kkori.mini_festa.presentation.model.TicketModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TicketModelsMapper implements Mapper<List<Ticket>, List<TicketModel>> {

    private EventFormat eventFormat;
    private boolean isSale = true;

    public TicketModelsMapper(EventFormat eventFormat) {
        this.eventFormat = eventFormat;
    }

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

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        try {
            Date startDate = eventFormat.getTransUKFormat().parse(saleStartDate);
            Date endDate = eventFormat.getTransUKFormat().parse(saleEndDate);

            calendarStart.setTime(startDate);
            calendarEnd.setTime(endDate);

            calendarStart.add(Calendar.HOUR, 9);
            calendarEnd.add(Calendar.HOUR, 9);

            long startTime = calendarStart.getTime().getTime();
            long endTime = calendarEnd.getTime().getTime();

            if (currentTime > startTime && currentTime < endTime) {
                long remainingTime = endTime - currentTime;
                Date remainDate = new Date(remainingTime);

                if (remainingTime > eventFormat.getYear()) {
                    return "약 " + eventFormat.getTransYearTicketFormat().format(remainDate) + "년 후에 판매마감";
                } else if (remainingTime > eventFormat.getMonth()) {
                    return "약 " + eventFormat.getTransMonthTicketFormat().format(remainDate) + "개월 후에 판매마감";
                } else if (remainingTime > eventFormat.getDay()) {
                    return eventFormat.getTransDayTicketFormat().format(remainDate) + "일 후에 판매마감";
                } else if (remainingTime > eventFormat.getHour()) {
                    return eventFormat.getTransHourTicketFormat().format(remainDate) + "시간 후에 판매마감";
                } else if (remainingTime > eventFormat.getMinute()) {
                    return eventFormat.getTransMinuteTicketFormat().format(remainDate) + "분 후에 판매마감";
                } else {
                    return eventFormat.getTransSecondTicketFormat().format(remainDate) + "초 후에 판매마감";
                }
            } else {
                if (currentTime < startTime) {
                    long remainingTime = currentTime - startTime;
                    Date remainDate = new Date(remainingTime);

                    if (remainingTime > eventFormat.getYear()) {
                        return "약 " + eventFormat.getTransYearTicketFormat().format(remainDate) + "년 후에 판매시작";
                    } else if (remainingTime > eventFormat.getMonth()) {
                        return "약 " + eventFormat.getTransMonthTicketFormat().format(remainDate) + "개월 후에 판매시작";
                    } else if (remainingTime > eventFormat.getDay()) {
                        return eventFormat.getTransDayTicketFormat().format(remainDate) + "일 후에 판매시작";
                    } else if (remainingTime > eventFormat.getHour()) {
                        return eventFormat.getTransHourTicketFormat().format(remainDate) + "시간 후에 판매시작";
                    } else if (remainingTime > eventFormat.getMinute()) {
                        return eventFormat.getTransMinuteTicketFormat().format(remainDate) + "분 후에 판매시작";
                    } else {
                        return eventFormat.getTransSecondTicketFormat().format(remainDate) + "초 후에 판매시작";
                    }
                } else {
                    isSale = false;
                    return "판매기간이 종료되었습니다";
                }
            }
        } catch (ParseException e) {
            isSale = false;
            e.printStackTrace();
        }

        return "판매기간을 알 수 없습니다";
    }

}
