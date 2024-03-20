package com.example.tluschedule.data.models.tluModels.semester;

import com.example.tluschedule.data.models.JsonModelBase;

public class SchoolYear extends JsonModelBase {

    private int id;
    private String name;
    private String code;
    private int year;
    private Long startDate;
    private Long endDate;
    private int isSemester;

    public SchoolYear(int id, String name, String code, int year, Long startDate, Long endDate, int isSemester) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isSemester = isSemester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public int isSemester() {
        return isSemester;
    }

    public void setSemester(int semester) {
        isSemester = semester;
    }

}
