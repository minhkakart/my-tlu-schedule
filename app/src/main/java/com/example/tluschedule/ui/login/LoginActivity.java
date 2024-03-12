package com.example.tluschedule.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tluschedule.R;
import com.example.tluschedule.config.StaticValues;
import com.example.tluschedule.data.model.ReceiveToken;
import com.example.tluschedule.data.model.TLUs.semester.SemesterContent;
import com.example.tluschedule.data.model.TLUs.semester.SemesterReceiver;
import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.data.model.User;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.network.Client.TLUClient;
import com.example.tluschedule.network.Services.TluApiService;
import com.example.tluschedule.supporter.converter.JsonConverter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar loadingProgressBar;
    private Button loginButton;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadingProgressBar = findViewById(R.id.loading);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loginButton.setClickable(true);
        loginButton.setOnClickListener(v -> login());

    }

    // Other methods
    void login() {
        startLoading();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username or password is empty", Toast.LENGTH_SHORT).show();
            stopLoading();
            return;
        }

        // Create an instance of TLUClient
        TluApiService tluApiService = TLUClient.getInstance();

        // Get authenticate token
        Call<ReceiveToken> getTokenCall = tluApiService.getToken(new User(username, password, "education_client", "password", "password"));
        getTokenCall.enqueue(new LoginCallback<ReceiveToken>() {
            @Override
            public void onFinished(@NonNull Call<ReceiveToken> call, @Nullable Response<ReceiveToken> response, @Nullable Throwable t) {
                // If get token success
                if (response != null && response.isSuccessful()) {
                    ReceiveToken token = response.body();
                    // Get semester info
                    assert token != null;
                    Call<SemesterReceiver> callSemester = tluApiService.getSemesterInfo("Bearer " + token.getAccess_token());
                    callSemester.enqueue(new LoginCallback<SemesterReceiver>() {
                        @Override
                        public void onFinished(@NonNull Call<SemesterReceiver> call, @Nullable Response<SemesterReceiver> response, @Nullable Throwable t) {
                            if (response != null && response.isSuccessful()) {
                                SemesterReceiver semesterReceiver = response.body();
                                // Find current semester
                                assert semesterReceiver != null;
                                for (SemesterContent semesterContent : semesterReceiver.getContent()) {
                                    if (semesterContent.isCurrent()) {
                                        // Get student course subject
                                        Call<List<Course>> callCourse = tluApiService.getStudentCourseSubject("Bearer " + token.getAccess_token(), semesterContent.getId());
                                        callCourse.enqueue(new LoginCallback<List<Course>>() {
                                            @Override
                                            public void onFinished(@NonNull Call<List<Course>> call, @Nullable Response<List<Course>> response, @Nullable Throwable t) {
                                                if (response != null && response.isSuccessful()) {
                                                    List<Course> courses = response.body();
                                                    assert courses != null;

                                                    FileActions.createAndWriteFile(LoginActivity.this, StaticValues.CURRENT_SEMESTER_FILE_NAME, semesterContent.toJsonString());
                                                    FileActions.createAndWriteFile(LoginActivity.this, StaticValues.COURSES_FILE_NAME, JsonConverter.listToJsonString(courses));

                                                    stopLoading();
                                                    finish();
                                                }
                                                // If get student course subject fail
                                                else {
                                                    stopLoading();
                                                    Toast.makeText(LoginActivity.this, getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                        break;
                                    }
                                }
                            }
                            // If get semester info fail
                            else {
                                stopLoading();
                                Toast.makeText(LoginActivity.this, getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                // If get token fail
                else {
                    stopLoading();
                    Toast.makeText(LoginActivity.this, getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void startLoading() {
        loginButton.setClickable(false);
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    private void stopLoading() {
        loadingProgressBar.setVisibility(View.GONE);
        loginButton.setClickable(true);
    }

    private static abstract class LoginCallback<T> implements Callback<T> {
        private String message;

        @Override
        public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
            message = response.isSuccessful() ? "Success" : "";
            onFinished(call, response, null);
        }

        @Override
        public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
            message = "Failed: " + t.getMessage();
            onFinished(call, null, t);
        }

        public abstract void onFinished(@NonNull Call<T> call, @Nullable Response<T> response, @Nullable Throwable t);

        public String getMessage() {
            if (message == null || message.isEmpty())
                return "Failed! Please try again!";
            else
                return message;
        }
    }

}