package com.example.tluschedule.data.model.TLUs.studentCourse;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;

public class Subject extends JsonModelBase{
    private int id;
    private String subjectCode;
    private String subjectName;
    private String subjectNameEng;
    private int numberOfCredit;

    public Subject(int id, String subjectCode, String subjectName, String subjectNameEng, int numberOfCredit) {
        this.id = id;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectNameEng = subjectNameEng;
        this.numberOfCredit = numberOfCredit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectNameEng() {
        return subjectNameEng;
    }

    public void setSubjectNameEng(String subjectNameEng) {
        this.subjectNameEng = subjectNameEng;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
    }

    @Override
    public String toJsonString() {
        return "{" +
                "\"id\":" + id +
                ", \"subjectCode\":\"" + subjectCode + '\"' +
                ", \"subjectName\":\"" + subjectName + '\"' +
                ", \"subjectNameEng\":\"" + subjectNameEng + '\"' +
                ", \"numberOfCredit\":" + numberOfCredit +
                '}';
    }

}
