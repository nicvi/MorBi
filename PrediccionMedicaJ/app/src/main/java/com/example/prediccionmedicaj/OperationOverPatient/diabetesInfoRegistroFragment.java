package com.example.prediccionmedicaj.OperationOverPatient;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.prediccionmedicaj.R;
import com.example.prediccionmedicaj.clases.TextValidator;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelD;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelGI;
import com.example.prediccionmedicaj.objects.Diabetes;
import com.google.android.material.textfield.TextInputEditText;

public class diabetesInfoRegistroFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    //TextInputEditText
    private TextInputEditText txtIn_glucose, txtIn_bloodPressure, txtIn_skinThickness,
            txtIn_insulin, txtIn_bmi, txtIn_diabetesPF,txtIn_sleepDuration, txtIn_calorieIntake;
    private RadioGroup radioGroupExercise ;

    private Diabetes diabetes;

    private SharedViewModelD modelD;

    public diabetesInfoRegistroFragment() {
        // Required empty public constructor
    }

    public static diabetesInfoRegistroFragment newInstance(String param1, String param2) {
        diabetesInfoRegistroFragment fragment = new diabetesInfoRegistroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inicializar objeto diabetes
        diabetes = new Diabetes();
        // set default values
        diabetes.setExercise(0);
        diabetes.setHasDiabetes(-1);
        diabetes.setCalculateDiabetes(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_diabetes_info_registro, container, false);;
        inicializarViews(view);
        modelD = ViewModelProviders.of(requireActivity()).get(SharedViewModelD.class);
        return view;
    }

    private void inicializarViews(View view) {
        txtIn_glucose = view.findViewById(R.id.txtIn_glucose);
        txtIn_bloodPressure = view.findViewById(R.id.txtIn_bloodPressure);
        txtIn_skinThickness = view.findViewById(R.id.txtIn_skinThickness);
        txtIn_insulin = view.findViewById(R.id.txtIn_insulin);
        txtIn_bmi = view.findViewById(R.id.txtIn_bmi);
        txtIn_diabetesPF = view.findViewById(R.id.txtIn_diabetesPF);
        txtIn_sleepDuration = view.findViewById(R.id.txtIn_sleepDuration);
        txtIn_calorieIntake = view.findViewById(R.id.txtIn_calorieIntake);
        radioGroupExercise = view.findViewById(R.id.radioGroupExercise);

        txtIn_glucose.addTextChangedListener(new TextValidator(txtIn_glucose) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int glucosa = 0;
                try {
                    glucosa += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                diabetes.setGlucose(glucosa);
                enviarDataDiabetes(diabetes);
            }
        });

        txtIn_bloodPressure.addTextChangedListener(new TextValidator(txtIn_bloodPressure) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int presionSanguinea = 0;
                try {
                    presionSanguinea += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                diabetes.setBloodPressure(presionSanguinea);
                enviarDataDiabetes(diabetes);
            }
        });

        txtIn_skinThickness.addTextChangedListener(new TextValidator(txtIn_skinThickness) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int grosorPiel = 0;
                try {
                    grosorPiel += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                diabetes.setSkinThickness(grosorPiel);
                enviarDataDiabetes(diabetes);
            }
        });

        txtIn_insulin.addTextChangedListener(new TextValidator(txtIn_insulin) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int insulin = 0;
                try {
                    insulin += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                diabetes.setInsulin(insulin);
                enviarDataDiabetes(diabetes);
            }
        });

        txtIn_bmi.addTextChangedListener(new TextValidator(txtIn_bmi) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                float BMI = 0;
                try {
                    BMI += Float.parseFloat( data);
                }
                catch (NumberFormatException ignored) {
                }
                diabetes.setBmi(BMI);
                enviarDataDiabetes(diabetes);
            }
        });

        txtIn_diabetesPF.addTextChangedListener(new TextValidator(txtIn_diabetesPF) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                float diabetePF = 0;
                try {
                    diabetePF += Float.parseFloat( data);
                }
                catch (NumberFormatException ignored) {
                }
                diabetes.setDiabetesPF(diabetePF);
                enviarDataDiabetes(diabetes);
            }
        });

        txtIn_calorieIntake.addTextChangedListener(new TextValidator(txtIn_calorieIntake) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                float calorieIntake = 0;
                try {
                    calorieIntake += Float.parseFloat( data);
                }
                catch (NumberFormatException ignored) {
                }
                diabetes.setCalorieIntake(calorieIntake);
                enviarDataDiabetes(diabetes);
            }
        });

        radioGroupExercise.check(R.id.radioButtonNoRealiza);
        radioGroupExercise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.radioButtonNoRealiza) {
                    diabetes.setExercise(0);
                }else if (checkedId == R.id.radioButtonMañana){
                    diabetes.setExercise(1);
                }else if (checkedId == R.id.radioButtonNoche){
                    diabetes.setExercise(2);
                }else if (checkedId ==R.id.radioButtonMañana_Noche){
                    diabetes.setExercise(3);
                }
                enviarDataDiabetes(diabetes);
            }
        });

        txtIn_sleepDuration.addTextChangedListener(new TextValidator(txtIn_sleepDuration) {
            @Override
            public void validate(TextInputEditText textInputEditText, String data) {
                int horasSueño = 0;
                try {
                    horasSueño += Integer.parseInt( data);
                }
                catch (NumberFormatException ignored) {
                }
                diabetes.setSleepDuration(horasSueño);
                enviarDataDiabetes(diabetes);
            }
        });
    }

    public void enviarDataDiabetes(Diabetes dataObjDiabetes){
        modelD.setMessage((Object) dataObjDiabetes);
    }
}