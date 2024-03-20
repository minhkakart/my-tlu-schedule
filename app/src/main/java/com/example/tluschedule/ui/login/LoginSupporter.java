package com.example.tluschedule.ui.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tluschedule.config.ConstantValues;
import com.example.tluschedule.data.models.ReceiveToken;
import com.example.tluschedule.data.models.UserLoginData;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.network.clients.TLUClient;
import com.example.tluschedule.supporter.reducer.CallbackReduce;

import retrofit2.Call;
import retrofit2.Response;

public abstract class LoginSupporter {
    private final UserLoginData userLoginData;

    public LoginSupporter(UserLoginData userLoginData) {
        this.userLoginData = userLoginData;
        this.login(userLoginData);
    }

    private void login(UserLoginData userLoginData) {
        Call<ReceiveToken> getToken = TLUClient.getInstance().getToken(userLoginData);
        getToken.enqueue(new CallbackReduce<ReceiveToken>() {
            @Override
            public void onFinished(@NonNull Call<ReceiveToken> call, @Nullable Response<ReceiveToken> response, @Nullable Throwable t) {
                if (response != null && response.isSuccessful()) {
                    onLoginSuccess(response.body());
                } else {
                    onLoginFailed(ConstantValues.LOGIN_FAILED);
                }
            }
        });
    }

    public void saveUserLoginData(Context context) {
        FileActions.createOrReplaceFile(context, ConstantValues.LOGGED_USER_FILE_NAME, userLoginData.toJsonString());
    }

    public abstract void onLoginSuccess(ReceiveToken token);

    public abstract void onLoginFailed(String message);

}
