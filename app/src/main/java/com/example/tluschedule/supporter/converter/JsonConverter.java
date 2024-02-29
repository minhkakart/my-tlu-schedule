package com.example.tluschedule.supporter.converter;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public final class JsonConverter {
    public static Gson gson = new GsonBuilder().create();

    public static String listJsonToStringForFile(List<? extends JsonModelBase> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (JsonModelBase item : list) {
            stringBuilder.append(item.toJsonString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static <T> T jsonStringToObject(String jsonString, Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }

}
