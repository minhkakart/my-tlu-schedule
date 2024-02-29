package com.example.tluschedule.data.model.TLUs.studentCourse;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;
import com.example.tluschedule.data.model.TLUs.semester.SemesterSubject;

import java.util.List;

public class CourseSubject extends JsonModelBase {
    private String code;
    private String shortCode;
    private List<TimeTable> timetables;
    private int maxStudent;
    private int minStudent;
    private int numberStudent;
    private int courseSubjectType;
    private boolean expanded;
    private boolean isGrantAll;
    private boolean isDeniedAll;
    private String courseYearCode;
    private String displayName;
    private SemesterSubject semesterSubject;
    private int status;
    private int id;
    private int numberOfCredit;

    public CourseSubject(String code, String shortCode, List<TimeTable> timetables, int maxStudent, int minStudent, int numberStudent, int courseSubjectType, boolean expanded, boolean isGrantAll, boolean isDeniedAll, String courseYearCode, String displayName, SemesterSubject semesterSubject, int status, int id, int numberOfCredit) {
        this.code = code;
        this.shortCode = shortCode;
        this.timetables = timetables;
        this.maxStudent = maxStudent;
        this.minStudent = minStudent;
        this.numberStudent = numberStudent;
        this.courseSubjectType = courseSubjectType;
        this.expanded = expanded;
        this.isGrantAll = isGrantAll;
        this.isDeniedAll = isDeniedAll;
        this.courseYearCode = courseYearCode;
        this.displayName = displayName;
        this.semesterSubject = semesterSubject;
        this.status = status;
        this.id = id;
        this.numberOfCredit = numberOfCredit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public List<TimeTable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<TimeTable> timetables) {
        this.timetables = timetables;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public int getMinStudent() {
        return minStudent;
    }

    public void setMinStudent(int minStudent) {
        this.minStudent = minStudent;
    }

    public int getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(int numberStudent) {
        this.numberStudent = numberStudent;
    }

    public int getCourseSubjectType() {
        return courseSubjectType;
    }

    public void setCourseSubjectType(int courseSubjectType) {
        this.courseSubjectType = courseSubjectType;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isGrantAll() {
        return isGrantAll;
    }

    public void setGrantAll(boolean grantAll) {
        isGrantAll = grantAll;
    }

    public boolean isDeniedAll() {
        return isDeniedAll;
    }

    public void setDeniedAll(boolean deniedAll) {
        isDeniedAll = deniedAll;
    }

    public String getCourseYearCode() {
        return courseYearCode;
    }

    public void setCourseYearCode(String courseYearCode) {
        this.courseYearCode = courseYearCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public SemesterSubject getSemesterSubject() {
        return semesterSubject;
    }

    public void setSemesterSubject(SemesterSubject semesterSubject) {
        this.semesterSubject = semesterSubject;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
    }

}
