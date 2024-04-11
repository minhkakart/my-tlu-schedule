package com.example.tluschedule.data.models.tluModels.drl;

import com.example.tluschedule.data.models.JsonModelBase;
import com.example.tluschedule.data.models.tluModels.semester.SchoolYear;

import java.util.List;

public class SchoolYearBehaviorMark extends JsonModelBase {
    private float mark;
    private SchoolYear schoolYear;
    private String sort;
    private List<SemesterMark> semesterMarks;

    public float getMark() {
        return mark;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public String getSort() {
        return sort;
    }

    public List<SemesterMark> getSemesterMarks() {
        return semesterMarks;
    }
}
