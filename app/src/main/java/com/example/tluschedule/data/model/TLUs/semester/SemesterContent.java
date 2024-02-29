package com.example.tluschedule.data.model.TLUs.semester;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;
import com.example.tluschedule.data.model.TLUs.SchoolYear;

import java.util.List;

public class SemesterContent extends JsonModelBase{
    private int id;
    private String semesterCode;
    private String semesterName;
    private SchoolYear schoolYear;
    private Long startDate;
    private Long endDate;
    private int ordinalNumbers;
    private List<SemesterRegisterPeriod> semesterRegisterPeriods;
    private boolean isCurrent;

    public SemesterContent(int id, String semesterCode, String semesterName, SchoolYear schoolYear, Long startDate, Long endDate, int ordinalNumbers, List<SemesterRegisterPeriod> semesterRegisterPeriods, boolean isCurrent) {
        this.id = id;
        this.semesterCode = semesterCode;
        this.semesterName = semesterName;
        this.schoolYear = schoolYear;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ordinalNumbers = ordinalNumbers;
        this.semesterRegisterPeriods = semesterRegisterPeriods;
        this.isCurrent = isCurrent;
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

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
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

    public int getOrdinalNumbers() {
        return ordinalNumbers;
    }

    public void setOrdinalNumbers(int ordinalNumbers) {
        this.ordinalNumbers = ordinalNumbers;
    }

    public List<SemesterRegisterPeriod> getSemesterRegisterPeriods() {
        return semesterRegisterPeriods;
    }

    public void setSemesterRegisterPeriods(List<SemesterRegisterPeriod> semesterRegisterPeriods) {
        this.semesterRegisterPeriods = semesterRegisterPeriods;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

}
