package com.example.tluschedule.data.models.tluModels.userinfo;

import com.example.tluschedule.data.models.JsonModelBase;

import java.util.List;

public class Child extends JsonModelBase {
    public long id;
    public String code;
    public String name;
    public long departmentType;
    public String displayOrder;
    public long level;
    public String linePath;
    public boolean duplicate;
}
