package com.example.tluschedule.ui.main.tluFunctionFragment;

import android.view.View;

public class FunctionButtonModel {
    private final String content;
    private final Class<?> targetActivity;

    public FunctionButtonModel(String content, Class<?> targetActivity) {
        this.content = content;
        this.targetActivity = targetActivity;
    }

    public String getContent() {
        return content;
    }

    public Class<?> getTargetActivity() {
        return targetActivity;
    }

}
