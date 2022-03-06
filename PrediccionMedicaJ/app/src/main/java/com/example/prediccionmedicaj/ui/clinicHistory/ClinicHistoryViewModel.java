package com.example.prediccionmedicaj.ui.clinicHistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClinicHistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClinicHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ClinicHistory fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}