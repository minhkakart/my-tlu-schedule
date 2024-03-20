package com.example.tluschedule.data.models.tluModels.studentCourse;

import com.example.tluschedule.data.models.JsonModelBase;

public class TimeTableHour extends JsonModelBase {
    private String id;
    private String name;
    private Long start;
    private String startString;
    private Long end;
    private String endString;
    private int indexNumber;

    public TimeTableHour(String id, String name, Long start, String startString, Long end, String endString, int indexNumber) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.startString = startString;
        this.end = end;
        this.endString = endString;
        this.indexNumber = indexNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public String getStartString() {
        return startString;
    }

    public void setStartString(String startString) {
        this.startString = startString;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getEndString() {
        return endString;
    }

    public void setEndString(String endString) {
        this.endString = endString;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

}
