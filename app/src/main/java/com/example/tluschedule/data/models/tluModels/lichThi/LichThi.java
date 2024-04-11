package com.example.tluschedule.data.models.tluModels.lichThi;

import com.example.tluschedule.data.models.JsonModelBase;

public class LichThi extends JsonModelBase {
    private int courseSubjectId;
    private String examCode;
    private int examCodeNumber;
    private String examPeriodCode;
    private ExamRoom examRoom;
    private int examRound;
    private int id;
    private int status;
    private String subjectName;

    public int getCourseSubjectId() {
        return courseSubjectId;
    }

    public String getExamCode() {
        return examCode;
    }

    public int getExamCodeNumber() {
        return examCodeNumber;
    }

    public String getExamPeriodCode() {
        return examPeriodCode;
    }

    public ExamRoom getExamRoom() {
        return examRoom;
    }

    public int getExamRound() {
        return examRound;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
