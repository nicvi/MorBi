package com.example.prediccionmedicaj.clases.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModelFC extends ViewModel {
    private final MutableLiveData messageFC = new MutableLiveData();

    public void setMessage(Object msg){
        messageFC.setValue(msg);
    }

    public MutableLiveData getMessage() {
        return messageFC;
    }
}