package com.example.tluschedule.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tluschedule.MainActivity;
import com.example.tluschedule.R;
import com.example.tluschedule.supporter.converter.ListJsonConverter;
import com.example.tluschedule.data.model.ReceiveToken;
import com.example.tluschedule.data.model.TLUs.semester.SemesterContent;
import com.example.tluschedule.data.model.TLUs.semester.SemesterReceiver;
import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.data.model.User;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.network.Client.TLUClient;
import com.example.tluschedule.network.Services.TluApiService;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ProgressBar loadingProgressBar;
    Button loginButton;
    Button loginBackButton;
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadingProgressBar = findViewById(R.id.loading);
        loginButton = findViewById(R.id.login);
        loginBackButton = findViewById(R.id.btn_login_back);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        loadingProgressBar.setVisibility(View.GONE);

        loginButton.setClickable(true);
        loginButton.setOnClickListener(v -> login());
        loginBackButton.setClickable(true);
        loginBackButton.setOnClickListener(v -> back());

    }

    private void back(){
        finish();
        Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }

    void login() {
        startLoading();

        TextView loginResult = findViewById(R.id.token);
        TextView semesterInfo = findViewById(R.id.semester);

        loginResult.setText("Loading...");
        semesterInfo.setText("Loading...");

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Create an instance of TLUClient
        TluApiService tluApiService = TLUClient.getInstance();

        // Get authenticate token
        Call<ReceiveToken> call = tluApiService.getToken(new User(username, password, "education_client", "password", "password"));
        call.enqueue(new Callback<ReceiveToken>(){
            // If request success
            @Override
            public void onResponse(@NonNull Call<ReceiveToken> call, @NonNull Response<ReceiveToken> response) {
                // If get token success
                if (response.isSuccessful()) {
                    ReceiveToken token = response.body();
                    assert token != null;
                    loginResult.setText(token.toString());

                    // Get semester info
                    Call<SemesterReceiver> callSemester = tluApiService.getSemesterInfo("Bearer " + token.getAccess_token());
                    callSemester.enqueue(new Callback<SemesterReceiver>() {
                        // If request success
                        @Override
                        public void onResponse(@NonNull Call<SemesterReceiver> call, @NonNull Response<SemesterReceiver> response) {
                            // If get semester info success
                            if (response.isSuccessful()) {
                                SemesterReceiver semesterReciver = response.body();
                                Date now = new Date();
                                assert semesterReciver != null;
                                // Find current semester
                                for (SemesterContent semesterContent : semesterReciver.getContent()) {
                                    if(semesterContent.getStartDate() <= now.getTime() && now.getTime() <= semesterContent.getEndDate()){
                                        // Get student course subject
                                        Call<List<Course>> callCourse = tluApiService.getStudentCourseSubject("Bearer " + token.getAccess_token(), semesterContent.getId());
                                        callCourse.enqueue(new Callback<List<Course>>() {
                                            @Override
                                            public void onResponse(@NonNull Call<List<Course>> call, @NonNull Response<List<Course>> response) {
                                                if (response.isSuccessful()) {
                                                    List<Course> courses = response.body();
                                                    assert courses != null;
                                                    semesterInfo.setText(ListJsonConverter.convertListJsonToString(courses));
                                                    stopLoading();

                                                    FileActions.createAndWriteFile(LoginActivity.this, "courses.txt", ListJsonConverter.convertListJsonToStringForFile(courses));

                                                    finish();

                                                    // Start main activity
                                                    Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(mainActivity);
                                                } else {
                                                    semesterInfo.setText(response.message());
                                                    stopLoading();
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<List<Course>> call, @NonNull Throwable t) {
                                                semesterInfo.setText(t.getMessage());
                                                stopLoading();
                                            }
                                        });
                                        break;
                                    }
                                }
                            }
                            // If get semester info fail
                            else {
                                semesterInfo.setText(response.message());
                                stopLoading();
                            }
                        }
                        // If request fail
                        @Override
                        public void onFailure(@NonNull Call<SemesterReceiver> call, @NonNull Throwable t) {
                            semesterInfo.setText(t.getMessage());
                            stopLoading();
                        }
                    });
                }
                // If get token fail
                else {
                    loginResult.setText(response.message());
                    stopLoading();
                }
            }
            // If request fail
            @Override
            public void onFailure(@NonNull Call<ReceiveToken> call, @NonNull Throwable t) {
                loginResult.setText(t.getMessage());
                stopLoading();
            }
        });
    }
    // Other methods
    private void startLoading() {
        loginButton.setClickable(false);
        loginBackButton.setClickable(false);
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    private void stopLoading() {
        loadingProgressBar.setVisibility(View.GONE);
        loginButton.setClickable(true);
        loginBackButton.setClickable(true);
    }

}