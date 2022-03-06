package com.example.prediccionmedicaj.clases.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModelD extends ViewModel {
    private final MutableLiveData messageD = new MutableLiveData();

    public void setMessage(Object msg){
        messageD.setValue(msg);
    }

    public MutableLiveData getMessage() {
        return messageD;
    }
}
