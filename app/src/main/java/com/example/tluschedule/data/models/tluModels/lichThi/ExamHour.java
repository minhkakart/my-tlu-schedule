package com.example.tluschedule.data.models.tluModels.lichThi;

import com.example.tluschedule.data.models.JsonModelBase;

public class ExamHour extends JsonModelBase {
    private long id;
    private long start;
    private String startString;
    private long end;
    private String endString;
    private String name;
    private String code;
    private boolean duplicate;

    public long getId() {
        return id;
    }

    public long getStart() {
        return start;
    }

    public String getStartString() {
        return startString;
    }

    public long getEnd() {
        return end;
    }

    public String getEndString() {
        return endString;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isDuplicate() {
        return duplicate;
    }
}
