package com.example.tluschedule.ui.main;

import com.example.tluschedule.data.model.TLUs.studentCourse.TimeTableHour;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class CourseDisplayModel {
    private final String name;
    private final String room;
    private final TimeTableHour startTime;
    private final TimeTableHour endTime;
    private final Date day;
    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("vi", "VN"));

    public CourseDisplayModel(String name, String room, TimeTableHour startTime, TimeTableHour endTime, Date day) {
        this.name = name;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public TimeTableHour getStartTime() {
        return startTime;
    }

    public TimeTableHour getEndTime() {
        return endTime;
    }

    public Date getDay() {
        return day;
    }

    public String getDayString() {

        return dateFormat.format(day);
    }
}
