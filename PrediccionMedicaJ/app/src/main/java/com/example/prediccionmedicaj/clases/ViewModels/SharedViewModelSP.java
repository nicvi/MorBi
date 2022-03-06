package com.example.prediccionmedicaj.clases.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// [BEGIN]<===================|| THE CALL OF THIS CLASS CAUSE A NULL INCIDENT, CALLING IT FROM THE "SelectedPatientFragment" ||==================== //
// * I only can get the value from the previous fragment getting inside the inner method, "modelSP.getMessageST().observe...",
// * after saved the value in a general variable and try to use it outside the inner method, the value of the variable is null.
// * Also, the println done from inside the inner method happens after the println from outside the inner method,
// * and after another println which is located after the invocation of the method "patientObtainedFromPreviousFragment"

public class SharedViewModelSP extends ViewModel {
    private final MutableLiveData messageSP = new MutableLiveData();

    public void setMessageST(String patientStr){
        messageSP.setValue(patientStr);
    }

    public MutableLiveData getMessageST() {
        return messageSP;
    }
}