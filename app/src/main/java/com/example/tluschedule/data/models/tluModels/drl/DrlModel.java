package com.example.tluschedule.data.models.tluModels.drl;

import com.example.tluschedule.data.models.JsonModelBase;

import java.util.List;

public class DrlModel extends JsonModelBase {
    private float mark;
    private String sort;
    private List<SchoolYearBehaviorMark> schoolYearBehaviorMarks;

    public float getMark() {
        return mark;
    }

    public String getSort() {
        return sort;
    }

    public List<SchoolYearBehaviorMark> getSchoolYearBehaviorMarks() {
        return schoolYearBehaviorMarks;
    }
}
