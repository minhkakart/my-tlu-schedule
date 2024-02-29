package com.example.tluschedule.data.model.TLUs;

import com.example.tluschedule.supporter.converter.JsonConverter;
import com.google.gson.Gson;

import java.util.List;

public abstract class JsonModelBase {
    private static final Gson gson = new Gson();

    public String toJsonArrayString(List<? extends JsonModelBase> list) {
        return JsonConverter.listJsonToString(list);
    }

    public final String toJsonString() {
        return gson.toJson(this);
    }
}
