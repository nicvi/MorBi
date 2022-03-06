package com.example.prediccionmedicaj.ui.pacientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.prediccionmedicaj.R;

public class PacientesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout LinearLayoutAddPatient, LinearLayoutModifyPatient, LinearLayoutConsultIll;

    private PacientesFragment binding;

    public PacientesFragment() {
        // Required empty public constructor
    }

    public static PacientesFragment newInstance(String param1, String param2) {
        PacientesFragment fragment = new PacientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_pacientes, container, false);
        LinearLayoutAddPatient = view.findViewById(R.id.LinearLayoutAddPatient);
        LinearLayoutModifyPatient = view.findViewById(R.id.LinearLayoutModifyPatient);
        LinearLayoutConsultIll = view.findViewById(R.id.LinearLayoutConsultIll);
        LinearLayoutAddPatient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_pacientes_to_createPatientFragment);
            }
        });
        LinearLayoutModifyPatient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // redirect to the desired menu
            }
        });;
        LinearLayoutConsultIll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_pacientes_to_nav_historial_consultas);
            }
        });;
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}