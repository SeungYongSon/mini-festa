package com.kkori.mini_festa.presentation.mapper;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

public class EventFormat {

    private SimpleDateFormat transUKFormat;
    private SimpleDateFormat transStartDateEventFormat;
    private SimpleDateFormat transSameDayEventFormat;
    private SimpleDateFormat transSameDayEndTimeEventFormat;
    private SimpleDateFormat transDifferentMonthAndDayEventFormat;
    private SimpleDateFormat transDifferentMonthAndDayEndTimeEventFormat;
    private SimpleDateFormat transDifferentYearEventFormat;
    private SimpleDateFormat transYearTicketFormat;
    private SimpleDateFormat transMonthTicketFormat;
    private SimpleDateFormat transDayTicketFormat;
    private SimpleDateFormat transHourTicketFormat;
    private SimpleDateFormat transMinuteTicketFormat;
    private SimpleDateFormat transSecondTicketFormat;

    @Inject
    EventFormat() {
        transUKFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
        transStartDateEventFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a h:mm", Locale.KOREA);
        transSameDayEventFormat = new SimpleDateFormat("yyyy년 MM월 dd일 (E),a hh:mm", Locale.KOREA);
        transSameDayEndTimeEventFormat = new SimpleDateFormat("a hh:mm", Locale.KOREA);
        transDifferentMonthAndDayEventFormat = new SimpleDateFormat("yyyy년 MM월 dd일 (E) a hh:mm", Locale.KOREA);
        transDifferentMonthAndDayEndTimeEventFormat = new SimpleDateFormat("MM월 dd일 (E) a hh:mm", Locale.KOREA);
        transDifferentYearEventFormat = new SimpleDateFormat("yyyy년 MM월 dd일 (E) a hh:mm", Locale.KOREA);
        transYearTicketFormat = new SimpleDateFormat("y", Locale.KOREA);
        transMonthTicketFormat = new SimpleDateFormat("M", Locale.KOREA);
        transDayTicketFormat = new SimpleDateFormat("d", Locale.KOREA);
        transHourTicketFormat = new SimpleDateFormat("h", Locale.KOREA);
        transMinuteTicketFormat = new SimpleDateFormat("m", Locale.KOREA);
        transSecondTicketFormat = new SimpleDateFormat("s", Locale.KOREA);
    }

    SimpleDateFormat getTransUKFormat() {
        return transUKFormat;
    }

    SimpleDateFormat getTransStartDateEventFormat() {
        return transStartDateEventFormat;
    }

    SimpleDateFormat getTransSameDayEventFormat() {
        return transSameDayEventFormat;
    }

    SimpleDateFormat getTransSameDayEndTimeEventFormat() {
        return transSameDayEndTimeEventFormat;
    }

    SimpleDateFormat getTransDifferentMonthAndDayEventFormat() {
        return transDifferentMonthAndDayEventFormat;
    }

    SimpleDateFormat getTransDifferentMonthAndDayEndTimeEventFormat() {
        return transDifferentMonthAndDayEndTimeEventFormat;
    }

    SimpleDateFormat getTransDifferentYearEventFormat() {
        return transDifferentYearEventFormat;
    }

    SimpleDateFormat getTransYearTicketFormat() {
        return transYearTicketFormat;
    }

    SimpleDateFormat getTransMonthTicketFormat() {
        return transMonthTicketFormat;
    }

    SimpleDateFormat getTransDayTicketFormat() {
        return transDayTicketFormat;
    }

    SimpleDateFormat getTransHourTicketFormat() {
        return transHourTicketFormat;
    }

    SimpleDateFormat getTransMinuteTicketFormat() {
        return transMinuteTicketFormat;
    }

    SimpleDateFormat getTransSecondTicketFormat() {
        return transSecondTicketFormat;
    }

    long getYear() {
        return Long.parseLong("15768000000");
    }

    long getMonth() {
        return 1296000000;
    }

    long getDay() {
        return 43200000;
    }

    long getHour() {
        return 3600000;
    }

    long getMinute() {
        return 60000;
    }

    String getPriceFormat() {
        return "￦ %s ~ ￦ %s";
    }
}
