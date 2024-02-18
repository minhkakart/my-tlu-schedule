package com.example.tluschedule.data.model.TLUs;

import com.example.tluschedule.supporter.converter.ListJsonConverter;

import java.util.List;

public abstract class JsonModelBase {
    public String toJsonArrayString(List<? extends JsonModelBase> list) {
        return ListJsonConverter.convertListJsonToString(list);
    }
    public abstract String toJsonString();
}
