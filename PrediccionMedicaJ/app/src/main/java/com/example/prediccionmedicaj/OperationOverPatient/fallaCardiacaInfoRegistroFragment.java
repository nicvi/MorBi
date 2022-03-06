package com.example.prediccionmedicaj.OperationOverPatient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.prediccionmedicaj.R;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelFC;
import com.example.prediccionmedicaj.clases.TextValidator;
import com.example.prediccionmedicaj.objects.FallaCardiaca;
import com.google.android.material.textfield.TextInputEditText;

public class fallaCardiacaInfoRegistroFragment extends Fragment {

    //TextInputEditText
    private TextInputEditText txtIn_creatinePP, txtIn_ejecFrac,  txtIn_platelets,
            txtIn_serumCreatine, txtIn_serumSodium, txtIn_time;
    private Switch switchHighPression, switchSmoke;

    private FallaCardiaca fallaCardiaca;

    private SharedViewModelFC model;

    public fallaCardiacaInfoRegistroFragment() {
        // Required empty public constructor
    }

    public static fallaCardiacaInfoRegistroFragment newInstance(String param1, String param2) {
        fallaCardiacaInfoRegistroFragment fragment = new fallaCardiacaInfoRegistroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // referenciar fragment view
        View view =inflater.inflate(R.layout.fragment_falla_cardiaca_info_registro, container, false);
        // inicializar objeto fallaCardiaca
        fallaCardiaca = new FallaCardiaca();

        // fallaCardiaca default values
        fallaCardiaca.setSmoking(0);
        fallaCardiaca.setHighBloodP(0);
        fallaCardiaca.setHasDeceased(-1);
        fallaCardiaca.setCalculateDeceased(0);
        //inicializar view del fragment
        inicializarViews(view);
        // viewModel
        model = ViewModelProviders.of(getActivity()).get(SharedViewModelFC.class);
        // Inflate the layout for this fragment
        return view;
    }

    // ******************* inicializarViews ******************** // [BEGIN]
    private void inicializarViews(View view) {
        txtIn_creatinePP = view.findViewById(R.id.txtIn_creatinePP);
        txtIn_ejecFrac = view.findViewById(R.id.txtIn_ejecFrac);
        switchHighPression = view.findViewById(R.id.switchHighPression);
        txtIn_platelets = view.findViewById(R.id.txtIn_platelets);
        txtIn_serumCreatine = view.findViewById(R.id.txtIn_serumCreatine);
        txtIn_serumSodium = view.findViewById(R.id.txtIn_serumSodium);
        switchSmoke = view.findViewById(R.id.switchSmoke);
        txtIn_time = view.findViewById(R.id.txtIn_time);

        txtIn_creatinePP.addTextChangedListener(new TextValidator(txtIn_creatinePP) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int creatinePP = 0;
                // send the data
                try {
                    creatinePP += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {

                }
                fallaCardiaca.setCreatinePP(creatinePP);
                enviarDataFallaCardiaca(fallaCardiaca);
            }
        });

        txtIn_ejecFrac.addTextChangedListener(new TextValidator(txtIn_ejecFrac) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int ejecFrac = 0;
                // send the data
                try {
                    ejecFrac += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                fallaCardiaca.setEjecFrac(ejecFrac);
                enviarDataFallaCardiaca(fallaCardiaca);
            }
        });

        switchHighPression.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchHighPression.isChecked()){
                    fallaCardiaca.setHighBloodP(1);
                    enviarDataFallaCardiaca(fallaCardiaca);
                }else{
                    fallaCardiaca.setHighBloodP(0);
                    enviarDataFallaCardiaca(fallaCardiaca);
                }
            }
        });


        txtIn_platelets.addTextChangedListener(new TextValidator(txtIn_platelets) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int platelets = 0;
                // send the data
                try {
                    platelets += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                fallaCardiaca.setPlatelets(platelets);
                enviarDataFallaCardiaca(fallaCardiaca);
            }
        });


        txtIn_serumCreatine.addTextChangedListener(new TextValidator(txtIn_serumCreatine) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                float serumCreatine = 0;
                // send the data
                try {
                    serumCreatine += Float.parseFloat(data);
                }
                catch (NumberFormatException ignored) {
                }
                fallaCardiaca.setSerumCreatine(serumCreatine);
                enviarDataFallaCardiaca(fallaCardiaca);
            }
        });

        txtIn_serumSodium.addTextChangedListener(new TextValidator(txtIn_serumSodium) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int serumSodium = 0;
                // send the data
                try {
                    serumSodium += Integer.parseInt(data);
                }
                catch (NumberFormatException ignored) {
                }
                fallaCardiaca.setSerumSodium(serumSodium);
                enviarDataFallaCardiaca(fallaCardiaca);
            }
        });

        switchSmoke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchSmoke.isChecked()){
                    fallaCardiaca.setSmoking(1);
                    enviarDataFallaCardiaca(fallaCardiaca);
                }else{
                    fallaCardiaca.setSmoking(0);
                    enviarDataFallaCardiaca(fallaCardiaca);
                }
            }
        });

        txtIn_time.addTextChangedListener(new TextValidator(txtIn_time) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int time = 0;
                // send the data
                try {
                    time += Integer.parseInt( data);
                }
                catch (NumberFormatException nfe) {
                }
                fallaCardiaca.setTime(time);
                enviarDataFallaCardiaca(fallaCardiaca);
            }
        });
    }
    // ******************* inicializarViews ******************** // [END]

    public void enviarDataFallaCardiaca(FallaCardiaca fallaCardiaca){
        model.setMessage((Object) fallaCardiaca);
    }
}