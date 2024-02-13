package com.example.tluschedule.data.model.TLUs.studentCourse;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;

public class Course extends JsonModelBase {

    private Long id;
    private String subjectName;
    private String subjectCode;
    private int subjectId;
    private int status;
    private int numberOfCredit;
    private CourseSubject courseSubject;
    private boolean isParent;
    private int typeRegister;
    private int regType;
    private String createDate;

    public Course(Long id, String subjectName, String subjectCode, int subjectId, int status, int numberOfCredit, CourseSubject courseSubject, boolean isParent, int typeRegister, int regType, String createDate) {
        this.id = id;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.subjectId = subjectId;
        this.status = status;
        this.numberOfCredit = numberOfCredit;
        this.courseSubject = courseSubject;
        this.isParent = isParent;
        this.typeRegister = typeRegister;
        this.regType = regType;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
    }

    public CourseSubject getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(CourseSubject courseSubject) {
        this.courseSubject = courseSubject;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public int getTypeRegister() {
        return typeRegister;
    }

    public void setTypeRegister(int typeRegister) {
        this.typeRegister = typeRegister;
    }

    public int getRegType() {
        return regType;
    }

    public void setRegType(int regType) {
        this.regType = regType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toJsonString() {
        return "{" +
                "    \"id\": " + id + "," +
                "    \"subjectName\": \"" + subjectName + "\"," +
                "    \"subjectCode\": \"" + subjectCode + "\"," +
                "    \"subjectId\": " + subjectId + "," +
                "    \"status\": " + status + "," +
                "    \"numberOfCredit\": " + numberOfCredit + "," +
                "    \"courseSubject\": " + courseSubject.toJsonString() + "," +
                "    \"isParent\": " + isParent + "," +
                "    \"typeRegister\": " + typeRegister + "," +
                "    \"regType\": " + regType + "," +
                "    \"createDate\": \"" + createDate + "\"" +
                "}";
    }
}
