package com.example.tluschedule.supporter.converter;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

public final class JsonConverter {
    public static Gson gson = new GsonBuilder().create();

    public static String listJsonToString(List<? extends JsonModelBase> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (JsonModelBase item : list) {
            stringBuilder.append(item.toJsonString());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

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
