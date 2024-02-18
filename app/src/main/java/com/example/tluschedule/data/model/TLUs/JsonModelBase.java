package com.example.tluschedule.data.model.TLUs;

import com.example.tluschedule.supporter.converter.JsonConverter;
import com.google.gson.Gson;

import java.util.List;

public abstract class JsonModelBase {
    public String toJsonArrayString(List<? extends JsonModelBase> list) {
        return JsonConverter.listJsonToString(list);
    }
    public abstract String toJsonString();
}
