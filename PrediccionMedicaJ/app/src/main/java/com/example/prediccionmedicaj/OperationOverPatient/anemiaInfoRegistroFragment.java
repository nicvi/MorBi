package com.example.prediccionmedicaj.OperationOverPatient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prediccionmedicaj.R;
import com.example.prediccionmedicaj.clases.TextValidator;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelA;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelGI;
import com.example.prediccionmedicaj.objects.Anemia;
import com.google.android.material.textfield.TextInputEditText;

public class anemiaInfoRegistroFragment extends Fragment {

    private int someStateValue;
    private final String SOME_VALUE_KEY = "someValueToSave";

    private TextInputEditText txtIn_hemoglobin, txtIn_mch, txtIn_mchc, txtIn_mcv;
    private Anemia anemia;
    private SharedViewModelA modelA;

    public anemiaInfoRegistroFragment() {
        // Required empty public constructor
    }

    public static anemiaInfoRegistroFragment newInstance(String param1, String param2) {
        anemiaInfoRegistroFragment fragment = new anemiaInfoRegistroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        anemia = new Anemia();
        anemia.setHasAnaemia(-1);
        anemia.setCalculateAnaemia(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anemia_info_registro, container, false);
        if (savedInstanceState != null) {
            someStateValue = savedInstanceState.getInt(SOME_VALUE_KEY);
        }
        inicializarViews(view);
        modelA = ViewModelProviders.of(getActivity()).get(SharedViewModelA.class);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SOME_VALUE_KEY, someStateValue);
        super.onSaveInstanceState(outState);
    }

    private void inicializarViews(View view){
        txtIn_hemoglobin =  view.findViewById(R.id.txtIn_hemoglobin);
        txtIn_mch =  view.findViewById(R.id.txtIn_mch);
        txtIn_mchc =  view.findViewById(R.id.txtIn_mchc);
        txtIn_mcv =  view.findViewById(R.id.txtIn_mcv);
        txtIn_hemoglobin.addTextChangedListener(new TextValidator(txtIn_hemoglobin) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                float hemoglobInData = 0;
                try {
                    hemoglobInData += Float.parseFloat( data);
                }
                catch (NumberFormatException ignored) {
                }
                anemia.setHemoglobin(hemoglobInData);
                enviarDataAnemia(anemia);
            }
        });

        txtIn_mch.addTextChangedListener(new TextValidator(txtIn_mch) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                float mchInData = 0;
                try {
                    mchInData += Float.parseFloat( data);
                }
                catch (NumberFormatException ignored) {
                }
                anemia.setMch(mchInData);
                enviarDataAnemia(anemia);
            }
        });

        txtIn_mchc.addTextChangedListener(new TextValidator(txtIn_mchc) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                float mchcInData = 0;
                try {
                    mchcInData += Float.parseFloat(data);
                }
                catch (NumberFormatException ignored) {
                }
                anemia.setMchc(mchcInData);
                enviarDataAnemia(anemia);
            }
        });

        txtIn_mcv.addTextChangedListener(new TextValidator(txtIn_mcv) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                float mcvInData = 0;
                try {
                    mcvInData += Float.parseFloat( data);
                }
                catch (NumberFormatException ignored) {
                }
                anemia.setMcv(mcvInData);
                enviarDataAnemia(anemia);
            }
        });
    }

    public void enviarDataAnemia(Anemia dataObjectAnemia){
        modelA.setMessage((Object) dataObjectAnemia);
    }

}