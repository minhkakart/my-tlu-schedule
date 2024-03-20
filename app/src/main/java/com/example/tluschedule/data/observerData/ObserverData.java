package com.example.tluschedule.data.observerData;

import androidx.lifecycle.Observer;

public class ObserverData<T> implements Observer<T> {
    private T data;

    @Override
    public void onChanged(T newData) {
        data = newData;
    }

    public void update(T newData) {
        onChanged(newData);
    }

    public T getData() {
        return data;
    }
}
