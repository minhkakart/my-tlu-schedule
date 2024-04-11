package com.example.tluschedule.data.models.tluModels.drl;

public class DrlDisplay {
    private String year;
    private String semester;
    private String drl;
    private String sort;

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getDrl() {
        return drl;
    }

    public String getSort() {
        return sort;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setDrl(String drl) {
        this.drl = drl;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
