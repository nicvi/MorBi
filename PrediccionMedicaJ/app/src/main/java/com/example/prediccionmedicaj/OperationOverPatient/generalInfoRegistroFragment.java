package com.example.prediccionmedicaj.OperationOverPatient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.prediccionmedicaj.R;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelGI;
import com.example.prediccionmedicaj.clases.TextValidator;
import com.example.prediccionmedicaj.objects.GeneralInfo;
import com.google.android.material.textfield.TextInputEditText;

public class generalInfoRegistroFragment extends Fragment {

    private TextInputEditText txtIn_nameNoJson, txtIn_type, txtIn_id, txtIn_age,txtIn_pregnancies;
    private Switch switchSex;
    private GeneralInfo generalInfo;
    private SharedViewModelGI modelGI;

    public generalInfoRegistroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generalInfo = new GeneralInfo();
        generalInfo.setGender(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_info_registro, container, false);
        inicializarViews(view);
        modelGI = ViewModelProviders.of(getActivity()).get(SharedViewModelGI.class);
        return view;
    }

    private void inicializarViews(View view) {
        txtIn_nameNoJson = view.findViewById(R.id.txtIn_nameNoJson);
        txtIn_type = view.findViewById(R.id.txtIn_type);
        txtIn_id = view.findViewById(R.id.txtIn_id);
        switchSex = view.findViewById(R.id.switchSex);
        txtIn_age = view.findViewById(R.id.txtIn_age);
        txtIn_pregnancies = view.findViewById(R.id.txtIn_pregnancies);
        txtIn_type.setFocusable(false);
        generalInfo.setType("Paciente");
        txtIn_nameNoJson.addTextChangedListener(new TextValidator(txtIn_nameNoJson) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                generalInfo.setNameNoJson(data);
                enviarDataGeneralInfo(generalInfo);
            }
        });
        switchSex.setChecked(false);
        switchSex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchSex.isChecked()){
                    generalInfo.setGender(1);
                    txtIn_pregnancies.setEnabled(false);
                    enviarDataGeneralInfo(generalInfo);
                }else{
                    generalInfo.setGender(0);
                    txtIn_pregnancies.setEnabled(true);
                    enviarDataGeneralInfo(generalInfo);
                }
            }
        });
        txtIn_id.addTextChangedListener(new TextValidator(txtIn_id) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                if (!data.equals("")) {
                    if (data.length()<10){
                        txtIn_id.setFocusable(true);
                        generalInfo.setId(Integer.parseInt(data));
                        enviarDataGeneralInfo(generalInfo);
                    }else{
                        txtIn_id.setFocusable(false);
                        Toast toast = Toast.makeText(requireActivity(), "Superó el limite de 10 dígitos", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                        toast.show();
                    }
                }
            }
        });
        txtIn_age.addTextChangedListener(new TextValidator(txtIn_age) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int ageData = 0;
                try {
                    ageData += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                generalInfo.setAge(ageData);
                enviarDataGeneralInfo(generalInfo);
            }
        });
        txtIn_pregnancies.addTextChangedListener(new TextValidator(txtIn_age) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int agePregnancies = 0;
                try {
                    agePregnancies += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                generalInfo.setPregnancies(agePregnancies);
                enviarDataGeneralInfo(generalInfo);
            }
        });
    }

    public void enviarDataGeneralInfo(GeneralInfo dataObjGeneralInfo){
        modelGI.setMessage((Object) dataObjGeneralInfo);
    }
}