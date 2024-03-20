package com.example.tluschedule.data.models;

import com.google.gson.Gson;

public abstract class JsonModelBase {
    private static final Gson gson = new Gson();
    public final String toJsonString() {
        return gson.toJson(this);
    }
}
