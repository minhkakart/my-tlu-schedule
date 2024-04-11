package com.example.tluschedule.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tluschedule.R;
import com.example.tluschedule.config.ConstantValues;
import com.example.tluschedule.data.models.ReceiveToken;
import com.example.tluschedule.data.models.UserLoginData;
import com.example.tluschedule.data.models.tluModels.userinfo.CurrentUser;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.network.clients.TLUClient;
import com.example.tluschedule.network.service.TluApiService;
import com.example.tluschedule.supporter.reducer.CallbackReduce;
import com.example.tluschedule.ui.login.LoginSupporter;

import retrofit2.Call;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.button);
        tv = findViewById(R.id.tv);

        UserLoginData user = FileActions.readSingleObjectFromFile(this, ConstantValues.LOGGED_USER_FILE_NAME, UserLoginData.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginSupporter(user) {

                    @Override
                    public void onLoginSuccess(ReceiveToken token) {
                        String accessToken = token.getAccess_token();
                        getCurrentUser(accessToken);
                    }

                    @Override
                    public void onLoginFailed(String message) {

                    }
                };

            }
        });

    }

    void getCurrentUser(String token) {
        TluApiService apiService = TLUClient.getInstance();
        Call<CurrentUser> call = apiService.getCurrentUser("Bearer " + token);
        call.enqueue(new CallbackReduce<CurrentUser>() {
            @Override
            public void onFinished(@NonNull Call<CurrentUser> call, @Nullable Response<CurrentUser> response, @Nullable Throwable t) {
                if (response.isSuccessful()) {
                    CurrentUser currentUser = response.body();
                    tv.setText(currentUser.displayName);
                }
            }
        });
    }

}