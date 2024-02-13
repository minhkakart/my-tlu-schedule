package com.example.tluschedule.data.model.TLUs.studentCourse;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;

public class Room extends JsonModelBase {
    private int id;
    private String name;
    private String code;
//    private String capacity;
//    private String examCapacity;
//    private String building;
//    private String dupName;
//    private String dupCode;
    private boolean duplicate;

    public Room(int id, String name, String code, boolean duplicate) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.duplicate = duplicate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public String toJsonString(){
        return "{\"id\":" + id
                + ",\"name\":\"" + name
                + "\",\"code\":\"" + code
                + "\",\"duplicate\":" + duplicate
                + "}";
    }

}
