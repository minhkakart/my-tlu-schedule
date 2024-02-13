package com.example.tluschedule.converter;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;

import java.util.List;

public final class ListJsonConverter {
    public static String convertListJsonToString(List<? extends JsonModelBase> list) {
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

    public static String convertListJsonToStringForFile(List<? extends JsonModelBase> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (JsonModelBase item : list) {
            stringBuilder.append(item.toJsonString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
