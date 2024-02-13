package com.example.tluschedule.ui.main;

public class CourseEg {
    private String name;
    private String time;
    private String room;

    public CourseEg(String name, String time, String room) {
        this.name = name;
        this.time = time;
        this.room = room;
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
}
