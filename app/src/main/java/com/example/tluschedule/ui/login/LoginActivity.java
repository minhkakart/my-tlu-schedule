package com.example.tluschedule.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tluschedule.R;
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
        loginButton = findViewById(R.id.login);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        loadingProgressBar.setVisibility(View.GONE);

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
        Call<ReceiveToken> call = tluApiService.getToken(new User(username, password, "education_client", "password", "password"));
        call.enqueue(new Callback<ReceiveToken>() {
            // If request success
            @Override
            public void onResponse(@NonNull Call<ReceiveToken> call, @NonNull Response<ReceiveToken> response) {
                // If get token success
                if (response.isSuccessful()) {
                    ReceiveToken token = response.body();

                    // Get semester info
                    assert token != null;
                    Call<SemesterReceiver> callSemester = tluApiService.getSemesterInfo("Bearer " + token.getAccess_token());
                    callSemester.enqueue(new Callback<SemesterReceiver>() {
                        // If request success
                        @Override
                        public void onResponse(@NonNull Call<SemesterReceiver> call, @NonNull Response<SemesterReceiver> response) {
                            // If get semester info success
                            if (response.isSuccessful()) {
                                SemesterReceiver semesterReceiver = response.body();
                                // Find current semester
                                assert semesterReceiver != null;
                                for (SemesterContent semesterContent : semesterReceiver.getContent()) {
                                    if (semesterContent.isCurrent()) {
                                        FileActions.createAndWriteFile(LoginActivity.this, "current_semester.txt", semesterContent.toJsonString());

                                        // Get student course subject
                                        Call<List<Course>> callCourse = tluApiService.getStudentCourseSubject("Bearer " + token.getAccess_token(), semesterContent.getId());
                                        callCourse.enqueue(new Callback<List<Course>>() {
                                            @Override
                                            public void onResponse(@NonNull Call<List<Course>> call, @NonNull Response<List<Course>> response) {
                                                if (response.isSuccessful()) {
                                                    List<Course> courses = response.body();
                                                    assert courses != null;
                                                    stopLoading();

                                                    FileActions.createAndWriteFile(LoginActivity.this, "courses.txt", JsonConverter.listToJsonStringListForFile(courses));

                                                    finish();
                                                } else {
                                                    stopLoading();
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<List<Course>> call, @NonNull Throwable t) {
                                                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                                stopLoading();
                                            }
                                        });
                                        break;
                                    }
                                }
                            }
                            // If get semester info fail
                            else {
                                Toast.makeText(LoginActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                                stopLoading();
                            }
                        }
                        // If request fail
                        @Override
                        public void onFailure(@NonNull Call<SemesterReceiver> call, @NonNull Throwable t) {
                            Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            stopLoading();
                        }
                    });
                }
                // If get token fail
                else {
                    Toast.makeText(LoginActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    stopLoading();
                }
            }
            // If request fail
            @Override
            public void onFailure(@NonNull Call<ReceiveToken> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                stopLoading();
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

}