package com.example.tluschedule.caculator;

import java.util.Calendar;
import java.util.Date;

public class CalendarCalculator {
    public static Date findLastWeekSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        calendar.add(Calendar.DATE, -dayOfWeek);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date findCurrentWeekSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        calendar.add(Calendar.DATE, 7 - dayOfWeek);

        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date findNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date increaseDateByOneDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date increaseDateByOneWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);
        return calendar.getTime();
    }

}
