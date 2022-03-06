package com.example.prediccionmedicaj.clases.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModelGI extends ViewModel {
    private final MutableLiveData messageGI = new MutableLiveData();

    public void setMessage(Object msg){
        messageGI.setValue(msg);
    }

    public MutableLiveData getMessage() {
        return messageGI;
    }
}
