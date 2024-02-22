package com.example.tluschedule.supporter.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarConverter {
    private static final Calendar calendar = new GregorianCalendar();

    public static int getHour(Date date) {

        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
}
