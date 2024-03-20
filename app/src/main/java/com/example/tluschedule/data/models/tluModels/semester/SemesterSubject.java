package com.example.tluschedule.data.models.tluModels.semester;

import com.example.tluschedule.data.models.JsonModelBase;
import com.example.tluschedule.data.models.tluModels.studentCourse.Subject;

public class SemesterSubject extends JsonModelBase {
    private int id;
    private Subject subject;
    private Semester semester;

    public SemesterSubject(int id, Subject subject, Semester semester) {
        this.id = id;
        this.subject = subject;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

}
