package com.example.tluschedule.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tluschedule.config.ConstantValues;
import com.example.tluschedule.data.models.ReceiveToken;
import com.example.tluschedule.data.models.UserLoginData;
import com.example.tluschedule.data.models.tluModels.semester.SemesterContent;
import com.example.tluschedule.data.models.tluModels.semester.SemesterReceiver;
import com.example.tluschedule.data.models.tluModels.studentCourse.Course;
import com.example.tluschedule.databinding.ActivityLoginBinding;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.network.clients.TLUClient;
import com.example.tluschedule.network.service.TluApiService;
import com.example.tluschedule.supporter.actionHandler.DoAfter;
import com.example.tluschedule.supporter.converter.JsonConverter;
import com.example.tluschedule.supporter.reducer.CallbackReduce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar loadingProgressBar;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private CheckBox rememberMeCheckBox;
    private TluApiService tluApiService;
    private UserLoginData userLoginData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingProgressBar = binding.loading;
        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.login;
        rememberMeCheckBox = binding.rememberMe;
        loginButton.setClickable(true);
        loginButton.setOnClickListener(v -> login());
        tluApiService = TLUClient.getInstance();

        // Check if user has logged in
        UserLoginData userLoginData = FileActions.readSingleObjectFromFile(this, ConstantValues.LOGGED_USER_FILE_NAME, UserLoginData.class);
        if (userLoginData != null) {
            usernameEditText.setText(userLoginData.getUsername());
            passwordEditText.setText(userLoginData.getPassword());
        }
    }

    void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username or password is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        startLoading();
        userLoginData = new UserLoginData(username, password);
        new LoginSupporter(userLoginData) {
            @Override
            public void onLoginSuccess(ReceiveToken receiveToken) {
                String token = receiveToken.getAccess_token();
                if (token != null) {
                    getAllSemester(token);
                    if (rememberMeCheckBox.isChecked()) {
                        saveUserLoginData(LoginActivity.this);
                    }
                } else {
                    stopLoading();
                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoginFailed(String message) {
                stopLoading();
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };

    }

    private void getAllCourse(String token, int semesterId, DoAfter doAfter) {
        Call<List<Course>> callCourse = tluApiService.getStudentCourseSubject("Bearer " + token, semesterId);
        callCourse.enqueue(new CallbackReduce<List<Course>>() {
            @Override
            public void onFinished(@NonNull Call<List<Course>> call, @Nullable Response<List<Course>> response, @Nullable Throwable t) {
                if (response != null && response.isSuccessful()) {
                    List<Course> courses = response.body();
                    if (courses == null) {
                        stopLoading();
                        Toast.makeText(LoginActivity.this, "Get course failed!", Toast.LENGTH_SHORT).show();
                    } else {
                        doAfter.doAfter();
                        FileActions.createOrReplaceFile(LoginActivity.this, ConstantValues.COURSES_FILE_NAME, JsonConverter.listToJsonString(courses));
                        stopLoading();
                        finish();
                    }
                } else {
                    stopLoading();
                    Toast.makeText(LoginActivity.this, getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getAllSemester(String token) {
        Call<SemesterReceiver> callSemester = tluApiService.getSemesterInfo("Bearer " + token);
        callSemester.enqueue(new CallbackReduce<SemesterReceiver>() {
            @Override
            public void onFinished(@NonNull Call<SemesterReceiver> call, @Nullable Response<SemesterReceiver> response, @Nullable Throwable t) {
                if (response != null && response.isSuccessful()) {
                    SemesterReceiver semesterReceiver = response.body();
                    if (semesterReceiver == null) {
                        stopLoading();
                        Toast.makeText(LoginActivity.this, "Get semester failed!", Toast.LENGTH_SHORT).show();
                    } else {
                        for (SemesterContent semesterContent : semesterReceiver.getContent()) {
                            if (semesterContent.isCurrent()) {
                                getAllCourse(token, semesterContent.getId(), () -> FileActions.createOrReplaceFile(LoginActivity.this, ConstantValues.CURRENT_SEMESTER_FILE_NAME, semesterContent.toJsonString()));
                                break;
                            }
                        }
                    }
                } else {
                    stopLoading();
                    Toast.makeText(LoginActivity.this, getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startLoading() {
        loginButton.setClickable(false);
        rememberMeCheckBox.setClickable(false);
        usernameEditText.setClickable(false);
        passwordEditText.setClickable(false);
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    private void stopLoading() {
        loadingProgressBar.setVisibility(View.GONE);
        loginButton.setClickable(true);
        rememberMeCheckBox.setClickable(true);
        usernameEditText.setClickable(true);
        passwordEditText.setClickable(true);
    }

}