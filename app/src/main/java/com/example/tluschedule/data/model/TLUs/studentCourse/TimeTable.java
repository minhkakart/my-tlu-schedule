package com.example.tluschedule.data.model.TLUs.studentCourse;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;

public class TimeTable extends JsonModelBase {
    private String id;
    private TimeTableHour endHour;
    private TimeTableHour startHour;
    private Room room;
    private int weekIndex;
    private int fromWeek;
    private int toWeek;
    private String start;
    private String end;
    private String roomName;
    private String roomCode;
    private Long startDate;
    private Long endDate;
    private int courseSubjectId;

    public TimeTable(String id, TimeTableHour endHour, TimeTableHour startHour, Room room, int weekIndex, int fromWeek, int toWeek, String start, String end, String roomName, String roomCode, Long startDate, Long endDate, int courseSubjectId) {
        this.id = id;
        this.endHour = endHour;
        this.startHour = startHour;
        this.room = room;
        this.weekIndex = weekIndex;
        this.fromWeek = fromWeek;
        this.toWeek = toWeek;
        this.start = start;
        this.end = end;
        this.roomName = roomName;
        this.roomCode = roomCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseSubjectId = courseSubjectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TimeTableHour getEndHour() {
        return endHour;
    }

    public void setEndHour(TimeTableHour endHour) {
        this.endHour = endHour;
    }

    public TimeTableHour getStartHour() {
        return startHour;
    }

    public void setStartHour(TimeTableHour startHour) {
        this.startHour = startHour;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(int weekIndex) {
        this.weekIndex = weekIndex;
    }

    public int getFromWeek() {
        return fromWeek;
    }

    public void setFromWeek(int fromWeek) {
        this.fromWeek = fromWeek;
    }

    public int getToWeek() {
        return toWeek;
    }

    public void setToWeek(int toWeek) {
        this.toWeek = toWeek;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public int getCourseSubjectId() {
        return courseSubjectId;
    }

    public void setCourseSubjectId(int courseSubjectId) {
        this.courseSubjectId = courseSubjectId;
    }

}
