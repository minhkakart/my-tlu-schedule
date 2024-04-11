package com.example.tluschedule.data.models.tluModels.drl;

import com.example.tluschedule.data.models.JsonModelBase;

public class Classification extends JsonModelBase {
    public int id;
    public int classification;
    public String classificationName;

    public int getId() {
        return id;
    }

    public int getClassification() {
        return classification;
    }

    public String getClassificationName() {
        return classificationName;
    }
}
