package com.example.tluschedule.data.model;

public class CourseItem {
    private String name;
    private String time;
    private String room;

    public CourseItem(String name, String time, String room) {
        this.name = name;
        this.time = time;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
