package com.example.mainactivity.ui.jetpack;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class JetpackViewModel extends ViewModel {
    private MutableLiveData<String> data;
    private MutableLiveData<User> userMutableLiveData;

    public MutableLiveData<User> getUserLiveData() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData();
        }
        return userMutableLiveData;
    }


    public LiveData<String> getData() {
        if (data == null) {
            data = new MutableLiveData<String>();
        }
        return data;
    }

    public void updateDataValue(String value) {
        data.postValue(value);
    }

}
