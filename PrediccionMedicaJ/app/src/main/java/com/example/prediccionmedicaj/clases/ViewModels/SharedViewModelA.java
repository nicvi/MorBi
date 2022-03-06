package com.example.prediccionmedicaj.clases.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModelA extends ViewModel {
    private final MutableLiveData messageA = new MutableLiveData();

    public void setMessage(Object msg) {
        messageA.setValue(msg);
    }

    public MutableLiveData getMessage() {
        return messageA;
    }
}