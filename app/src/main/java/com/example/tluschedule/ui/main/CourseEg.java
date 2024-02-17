package com.example.tluschedule.ui.main;

public class CourseEg {
    private String name;
    private String time;
    private String room;
    private String day;

    public CourseEg(String name, String time, String room, String day) {
        this.name = name;
        this.time = time;
        this.room = room;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getRoom() {
        return room;
    }

    public String getDay() {
        return day;
    }
}
