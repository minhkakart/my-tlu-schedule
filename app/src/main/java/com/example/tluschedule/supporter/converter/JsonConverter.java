package com.example.tluschedule.supporter.converter;

import android.util.Log;

import com.example.tluschedule.data.models.JsonModelBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public final class JsonConverter {
    public static Gson gson = new GsonBuilder().create();

    public static String listToJsonString(List<? extends JsonModelBase> list) {
        return gson.toJson(list);
    }

    public static <T> T jsonStringToObject(String jsonString, Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }

    public static <T> List<T> jsonStringToList(String jsonString, Class<T> classOfT) {
        StringReader reader = new StringReader(jsonString);
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);
        try {
            List<T> list = new ArrayList<>();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                T object = gson.fromJson(jsonReader, classOfT);
                list.add(object);
            }
            jsonReader.endArray();
            return list;

        } catch (Exception e) {
            Log.e("Json converter", "jsonStringToList: " + e.getMessage());
            return null;
        }
    }

}
