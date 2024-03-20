package com.example.tluschedule.supporter.reducer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CallbackReduce<T> implements Callback<T> {
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
