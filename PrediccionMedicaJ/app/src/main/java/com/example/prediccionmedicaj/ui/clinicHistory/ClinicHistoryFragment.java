package com.example.prediccionmedicaj.ui.clinicHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prediccionmedicaj.R;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelSP;
import com.example.prediccionmedicaj.databinding.FragmentClinicHistoryBinding;
import java.io.FileInputStream;
import java.io.IOException;


public class ClinicHistoryFragment extends Fragment {

    private ClinicHistoryViewModel slideshowViewModel;
    private FragmentClinicHistoryBinding binding;
    private String[] patientListStr;
    private RecyclerView patientsRecycler;
    private SharedViewModelSP modelSP;
    private FileInputStream fstream;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this).get(ClinicHistoryViewModel.class); // Here is using the viewModel
        binding = FragmentClinicHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        modelSP = ViewModelProviders.of(requireActivity()).get(SharedViewModelSP.class);
        patientsRecycler = (RecyclerView) binding.patientsRecyclerView;
        getPacients();
        toImageAdapter();
        return root;
    }


    public void getPacients(){
        try {
            fstream =  requireActivity().openFileInput("pacientes");
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            patientListStr = sbuffer.toString().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toImageAdapter()
    {
        if (patientListStr!=null && patientListStr.length>0){
            invertUsingFor(patientListStr);

            CardCaptionedImageAdapter AdapterForRecyclerVIew = new CardCaptionedImageAdapter(
                    patientListStr
            );
            patientsRecycler.setAdapter(AdapterForRecyclerVIew);
            LinearLayoutManager layoutManager = new LinearLayoutManager( requireActivity());
            patientsRecycler.setLayoutManager(layoutManager);
            AdapterForRecyclerVIew.setListener(new CardCaptionedImageAdapter.Listener() {
                @Override
                public void onClick(int positionEntityPatientJsonOnArray) {
                    Bundle bundlePatientString = new Bundle();

                    invertUsingFor(patientListStr);
                    positionEntityPatientJsonOnArray = (patientListStr.length - 1) - positionEntityPatientJsonOnArray;

                    bundlePatientString.putString("PATIENT_STRING",patientListStr[positionEntityPatientJsonOnArray]+"__"+positionEntityPatientJsonOnArray);
                    Navigation.findNavController(patientsRecycler).navigate(R.id.action_nav_historial_consultas_to_patientSelectedFragment, bundlePatientString);
                }
            });
        }
    }

    public void invertUsingFor(String[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            String temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}