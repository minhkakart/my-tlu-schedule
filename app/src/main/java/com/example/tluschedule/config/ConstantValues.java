package com.example.tluschedule.config;

import androidx.annotation.StringRes;

import com.example.tluschedule.MainActivity;
import com.example.tluschedule.R;
import com.example.tluschedule.ui.lichthi.LichThiActivity;
import com.example.tluschedule.ui.login.LoginActivity;
import com.example.tluschedule.ui.main.tluFunctionFragment.FunctionButtonModel;

import java.util.ArrayList;
import java.util.List;

public final class ConstantValues {
    public static final String COURSES_FILE_NAME = "courses.json";
    public static final String CURRENT_SEMESTER_FILE_NAME = "current_semester.json";
    public static final String CHANNEL_ID = "Course chanel";
    public static final String LOGGED_USER_FILE_NAME = "logged_user.json";
    @StringRes
    public static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    public static long ALARM_INTERVAL = 60 * 1000;
    public static final String LOGIN_FAILED = "Login failed! Please try again!";

    public static List<FunctionButtonModel> getListFunctionButtonModel() {
        List<FunctionButtonModel> functionButtonModels = new ArrayList<>();
        functionButtonModels.add(new FunctionButtonModel("Main Activity", MainActivity.class));
        functionButtonModels.add(new FunctionButtonModel("Login Activity", LoginActivity.class));
        functionButtonModels.add(new FunctionButtonModel("Lịch thi", LichThiActivity.class));
        return functionButtonModels;
    }
}
