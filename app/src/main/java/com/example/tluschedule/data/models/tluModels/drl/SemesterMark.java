package com.example.tluschedule.data.models.tluModels.drl;

import com.example.tluschedule.data.models.JsonModelBase;
import com.example.tluschedule.data.models.tluModels.semester.SchoolYear;
import com.example.tluschedule.data.models.tluModels.semester.Semester;

public class SemesterMark extends JsonModelBase {
    private int id;
    private Semester semester;
    private int mark;
    private String sort;
    private String code;
    private SchoolYear schoolYear;
    private int level;
    private double markConversion;
    private Classification classification;
    private int totalMarkStudent;
    private int totalMarkCVHT;
    private int totalMarktDepartment;
    private boolean isLock;
    private int countSemester;
    private int sortMark;

    public int getId() {
        return id;
    }

    public Semester getSemester() {
        return semester;
    }

    public int getMark() {
        return mark;
    }

    public String getSort() {
        return sort;
    }

    public String getCode() {
        return code;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public int getLevel() {
        return level;
    }

    public double getMarkConversion() {
        return markConversion;
    }

    public Classification getClassification() {
        return classification;
    }

    public int getTotalMarkStudent() {
        return totalMarkStudent;
    }

    public int getTotalMarkCVHT() {
        return totalMarkCVHT;
    }

    public int getTotalMarktDepartment() {
        return totalMarktDepartment;
    }

    public boolean isLock() {
        return isLock;
    }

    public int getCountSemester() {
        return countSemester;
    }

    public int getSortMark() {
        return sortMark;
    }
}
