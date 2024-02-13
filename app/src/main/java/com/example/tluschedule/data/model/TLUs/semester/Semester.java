package com.example.tluschedule.data.model.TLUs.semester;

import androidx.annotation.NonNull;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;

public class Semester extends JsonModelBase {
    private int id;
    private String semesterCode;
    private String semesterName;
    private Long startDate;
    private Long endDate;
    private Long startRegisterDate;
    private Long endRegisterDate;

    public Semester(int id, String semesterCode, String semesterName, Long startDate, Long endDate, Long startRegisterDate, Long endRegisterDate) {
        this.id = id;
        this.semesterCode = semesterCode;
        this.semesterName = semesterName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startRegisterDate = startRegisterDate;
        this.endRegisterDate = endRegisterDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
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

    public Long getStartRegisterDate() {
        return startRegisterDate;
    }

    public void setStartRegisterDate(Long startRegisterDate) {
        this.startRegisterDate = startRegisterDate;
    }

    public Long getEndRegisterDate() {
        return endRegisterDate;
    }

    public void setEndRegisterDate(Long endRegisterDate) {
        this.endRegisterDate = endRegisterDate;
    }

    @NonNull
    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", semesterCode='" + semesterCode + '\'' +
                ", semesterName='" + semesterName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startRegisterDate=" + startRegisterDate +
                ", endRegisterDate=" + endRegisterDate +
                '}';
    }

    public String toJsonString(){
        return "{" +
                "\"id\":" + id +
                ", \"semesterCode\":\"" + semesterCode + '\"' +
                ", \"semesterName\":\"" + semesterName + '\"' +
                ", \"startDate\":" + startDate +
                ", \"endDate\":" + endDate +
                ", \"startRegisterDate\":" + startRegisterDate +
                ", \"endRegisterDate\":" + endRegisterDate +
                '}';
    }

}
