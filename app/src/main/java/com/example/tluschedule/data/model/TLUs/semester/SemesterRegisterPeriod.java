package com.example.tluschedule.data.model.TLUs.semester;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;

public class SemesterRegisterPeriod extends JsonModelBase {
    private int id;
    private boolean voided;
    private Semester semester;
    private String name;
    private int displayOrder;

    public SemesterRegisterPeriod(int id, boolean voided, Semester semester, String name, int displayOrder) {
        this.id = id;
        this.voided = voided;
        this.semester = semester;
        this.name = name;
        this.displayOrder = displayOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

}
