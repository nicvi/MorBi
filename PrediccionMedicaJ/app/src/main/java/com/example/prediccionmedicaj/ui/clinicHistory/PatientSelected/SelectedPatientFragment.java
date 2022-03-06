package com.example.prediccionmedicaj.ui.clinicHistory.PatientSelected;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prediccionmedicaj.R;
import com.example.prediccionmedicaj.clases.StringToPatientEntyty;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelD;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelSP;
import com.example.prediccionmedicaj.objects.Diabetes;
import com.example.prediccionmedicaj.objects.Paciente;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;

import lombok.SneakyThrows;

public class SelectedPatientFragment extends Fragment {

    private SelectedPatientFragment binding;

    // patient
    private Paciente patient;
    private int startedHasDiabetes, startedHasDecease, startedHasAnemia, latestHasDiabetes,
            latestHasDecease, latestHasAnemia, patientPositionOnPatientArray, actualUserprofilePic;
    private String[] patientAndPositionFromPatientArray;
    private String actualPatientName, actualUserProfilePicStr;

    // Views
    private ImageButton imageButton_consultDecease, imageButton_consultDiabetes, imageButton_consultAnemia;
    private TextView textView_patientName, textView_patientAge, textView_patientSex, textView_patientId, textView_selectedPatientAnemiaConsulted,textView_selectedPatientDiabetesConsulted,textView_selectedPatientDeceaseConsult;
    private ImageView imageViewSemaphore, imageViewPatientSelected ;

    // Progress bar variables
    int tiempoEsperaConsulta = 7;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();

    // Orion // http://200.126.14.229:9090 (ESPOL) // http://192.168.100.7:1026/
    private Boolean updatedAnaemia =false, updatedDiabetes =false, updatedDecease =false;
    private final String orionRed = "200.126.14.229",
            port = ":9090",
            orionEntityPath = "/v2/entities/";

    public SelectedPatientFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @SneakyThrows
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_patient, container, false);
        patient = new Paciente();
        patientAndPositionFromPatientArray = ObtainSelectedPatientAndPositionInPatientArray().split("__");
        patientPositionOnPatientArray = Integer.parseInt(patientAndPositionFromPatientArray[1]);
        try {
            patient = new StringToPatientEntyty(patientAndPositionFromPatientArray[0]).convertToPatient();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // saving previous values
        actualPatientName = patient.getGeneralInfo().getNameNoJson();
        actualUserProfilePicStr = patient.getGeneralInfo().getUserprofilePic();

        // init views
        try {
            initViews(view);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
    public void initViews(View view) throws IOException, JSONException {
        // initialize views
        imageViewPatientSelected = view.findViewById(R.id.imageViewPatientSelected);
        textView_patientName = (TextView)  view.findViewById(R.id.textView_patientName);
        textView_patientAge = (TextView)  view.findViewById(R.id.textView_patientAge);
        textView_patientSex = (TextView)  view.findViewById(R.id.textView_patientSex);
        textView_patientId = (TextView)  view.findViewById(R.id.textView_patientId);
        imageViewSemaphore = view.findViewById(R.id.imageViewSemaphore);
        textView_selectedPatientAnemiaConsulted = (TextView)  view.findViewById(R.id.textView_selectedPatientAnemiaConsulted);;
        textView_selectedPatientDiabetesConsulted = (TextView)  view.findViewById(R.id.textView_selectedPatientDiabetesConsulted);;
        textView_selectedPatientDeceaseConsult = (TextView)  view.findViewById(R.id.textView_selectedPatientDeceaseConsult);

        imageButton_consultDecease = (ImageButton)  view.findViewById(R.id.imageButton_consultDecease);
        imageButton_consultDiabetes = (ImageButton)  view.findViewById(R.id.imageButton_consultDiabetes);
        imageButton_consultAnemia = (ImageButton)  view.findViewById(R.id.imageButton_consultAnemia);

        imageViewPatientSelected.setImageResource( requireContext().getResources().getIdentifier(patient.getGeneralInfo().getUserprofilePic() ,"drawable",requireContext().getPackageName()) );

        textView_patientName.setText(patient.getGeneralInfo().getNameNoJson());
        textView_patientAge.setText(getString(R.string.patientAge, patient.getGeneralInfo().getAge()));
        if (patient.getGeneralInfo().getGender() == 0 ){
            textView_patientSex.setText(getString( R.string.patientSex, "F" ));
        }
        else if(patient.getGeneralInfo().getGender() == 1){
            textView_patientSex.setText(getString( R.string.patientSex, "M" ));
        }
        textView_patientId.setText(getString(R.string.patientId, patient.getGeneralInfo().getId()));
        receiveEntityFromOrionAtStart();
        imageButton_consultAnemia.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                if (!updatedAnaemia) {
                    try {
                        updateHasDiseaseToOrion(1);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                LaunchProgressBar(view, 1);
            }
        });
        imageButton_consultDiabetes.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                if (!updatedDiabetes) {
                    try {
                        updateHasDiseaseToOrion(2);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                LaunchProgressBar(view, 2);
            }
        });
        imageButton_consultDecease.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                // consult falla caridaca only if "hasDeceased" and "hasDiabetes" were consulted.
                if (patient.getDiabetes().getHasDiabetes()!=-1 && patient.getAnemia().getHasAnaemia()!=-1)                {
                    // update the patient data to Orion just once
                    if (!updatedDecease){
                        try {
                            updateHasDiseaseToOrion(3);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    LaunchProgressBar(view, 3); // commented for test
                }
                else if (patient.getDiabetes().getHasDiabetes()==-1 && patient.getAnemia().getHasAnaemia()!=-1) {
                    Toast.makeText(requireActivity(), "Requiere consultar de diabetes", Toast.LENGTH_LONG).show();
                }else if (patient.getDiabetes().getHasDiabetes()!=-1 && patient.getAnemia().getHasAnaemia()==-1){
                    Toast.makeText(requireActivity(), "Requiere consultar de anemia", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(requireActivity(), "Consulte antes anemia y diabetes", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void setActualPatientEntity() {
        // set results
        setImageSemaphore(patient.getAnemia().getHasAnaemia(),
                patient.getDiabetes().getHasDiabetes(),
                patient.getFallaCardiaca().getHasDeceased()
        );
        setConsultAnswerOnAnemiaViews();
        setConsultAnswerOnDiabetesViews();
        setConsultAnswerOnDeceaseViews();

        startedHasAnemia = patient.getAnemia().getHasAnaemia() ;
        startedHasDiabetes = patient.getDiabetes().getHasDiabetes();
        startedHasDecease = patient.getFallaCardiaca().getHasDeceased();
        latestHasAnemia = startedHasAnemia ;
        latestHasDiabetes = startedHasDiabetes;
        latestHasDecease = startedHasDecease;
    }

    public void setConsultAnswerOnDiabetesViews(){
        if (patient.getDiabetes().getHasDiabetes()==1){
            textView_selectedPatientDiabetesConsulted.setText(R.string.diabetesHighProbability);
            textView_selectedPatientDiabetesConsulted.setTextColor(Color.parseColor("#FB7181"));
        }else if (patient.getDiabetes().getHasDiabetes()==0){
            textView_selectedPatientDiabetesConsulted.setText(R.string.diabetesLowProbability);
            textView_selectedPatientDiabetesConsulted.setTextColor(Color.parseColor("#52870F"));
        }else {
            textView_selectedPatientDiabetesConsulted.setText(R.string.diabetesConsult);
            textView_selectedPatientDiabetesConsulted.setTextColor(Color.parseColor("#63656B"));
        }
    }

    public void setConsultAnswerOnAnemiaViews(){
        // after received the answer from Orion
        if (patient.getAnemia().getHasAnaemia()==1){
            textView_selectedPatientAnemiaConsulted.setText(R.string.anemiaHighProbability);
            textView_selectedPatientAnemiaConsulted.setTextColor(Color.parseColor("#FB7181"));
        }
        else if(patient.getAnemia().getHasAnaemia()==0){
            textView_selectedPatientAnemiaConsulted.setText(R.string.anemiaLowProbability);
            textView_selectedPatientAnemiaConsulted.setTextColor(Color.parseColor("#52870F"));
        }else{
            textView_selectedPatientAnemiaConsulted.setText(R.string.anemiaConsult);
            textView_selectedPatientAnemiaConsulted.setTextColor(Color.parseColor("#63656B"));
        }
    }

    public void setConsultAnswerOnDeceaseViews(){
        // after received the answer from Orion
        if (patient.getFallaCardiaca().getHasDeceased()==1){
            textView_selectedPatientDeceaseConsult.setText(R.string.deceaseHighProbability);
            textView_selectedPatientDeceaseConsult.setTextColor(Color.parseColor("#FB7181"));
        }
        else if(patient.getFallaCardiaca().getHasDeceased()==0){
            textView_selectedPatientDeceaseConsult.setText(R.string.deceaseLowProbability);
            textView_selectedPatientDeceaseConsult.setTextColor(Color.parseColor("#52870F"));
        }else{
            textView_selectedPatientDeceaseConsult.setText(R.string.deceaseConsult);
            textView_selectedPatientDeceaseConsult.setTextColor(Color.parseColor("#63656B"));
        }
    }

    public void updateHasDiseaseToOrion(int disease) throws IOException, JSONException {
        String diseaseToCalculate= "";
        switch(disease){
            case 1: // anemia
                diseaseToCalculate = "calculateAnaemia"; // calculateAnaemia
                break;
            case 2: // diabetes
                diseaseToCalculate = "calculateDiabetes";
                break;
            case 3: // dcease
                diseaseToCalculate = "calculateDeceased";
                break;
        }
        String calculateDisease = "1";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // it allows to make the internet request
        StrictMode.setThreadPolicy(policy);
        URL url = new URL("http://"+orionRed+port+orionEntityPath+"p_"+patient.getGeneralInfo().getId()+"/attrs/"+diseaseToCalculate+"/value");  // 127.0.0.1 // 172.17.0.1 // 192.168.100.8:1026 // 10.0.2.2 (localHost of pc host, not emulator localHost) // ( 192.168.100.2 VM localhost)
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "text/plain");
        http.setRequestProperty("Accept", "text/plain");
        byte[] out = calculateDisease.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println("Updated paciente ORION http.getResponseCode: "+ http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        // end request
        if (http.getResponseCode()== 204){ // if the get request to Orion port was true
            switch(disease){
                case 1: // anemia
                    updatedAnaemia = true; // calculatedAnaemia set to true
                    break;
                case 2: // diabetes
                    updatedDiabetes = true; // calculatedDiabetes set to true
                    break;
                case 3: // dcease
                    updatedDecease = true; // calculatedDecease set to true
                    break;
            }
        }
    }


    public void receiveEntityFromOrion() throws IOException, JSONException {
        StringBuffer response = new StringBuffer();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // it allows to make the internet request
        StrictMode.setThreadPolicy(policy);
        // start request
        URL url = new URL("http://"+orionRed+port+orionEntityPath+"p_"+patient.getGeneralInfo().getId()); // 127.0.0.1 // 172.17.0.1 // 192.168.100.8:1026 // 10.0.2.2 (localHost of pc host, not emulator localHost) // ( 192.168.100.2 VM localhost)
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "*/*");
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
        }
        patient = new StringToPatientEntyty(response.toString()).convertToPatientNoName();
        http.disconnect();
        modifyPatientLocally();
    }

    //receive Entity From Orion At Start
    public void receiveEntityFromOrionAtStart() throws IOException, JSONException {
        StringBuffer response = new StringBuffer();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // it allows to make the internet request
        StrictMode.setThreadPolicy(policy);
        // start request
        URL url = new URL("http://"+orionRed+port+orionEntityPath+"p_"+patient.getGeneralInfo().getId()); // 127.0.0.1 // 172.17.0.1 // 192.168.100.8:1026 // 10.0.2.2 (localHost of pc host, not emulator localHost) // ( 192.168.100.2 VM localhost)
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "*/*");
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
        } else {
            System.out.println("GET request not worked");
        }
        patient = new StringToPatientEntyty(response.toString()).convertToPatientNoName();// update local patient with value received
        http.disconnect();
        setActualPatientEntity();
        modifyPatientLocally();
    }

    @SneakyThrows
    public String ObtainSelectedPatientAndPositionInPatientArray(){
        Bundle bundleStringPatient = getArguments();
        assert bundleStringPatient != null;

        return bundleStringPatient.getString("PATIENT_STRING");
    }

    public void modifyPatientLocally() throws IOException {
        FileInputStream fstreamIn =  requireActivity().openFileInput("pacientes");
        StringBuffer sbuffer = new StringBuffer();
        String[] patientsFromStrArray;
        // reading the patient files
        int i;
        while ((i = fstreamIn.read())!= -1){
            sbuffer.append((char)i);
        }
        fstreamIn.close();
        patientsFromStrArray = sbuffer.toString().split("\n");
        // setting previous values; name and imageCode
        patient.getGeneralInfo().setNameNoJson(actualPatientName);
        patient.getGeneralInfo().setUserprofilePic(actualUserProfilePicStr);
        // setting the new value to the entity
        patientsFromStrArray[patientPositionOnPatientArray]= patient.getJsonFullObject().toString();
        // updating patient values on file
        FileOutputStream fstreamOut = requireActivity().openFileOutput("pacientes", Context.MODE_PRIVATE);
        for (String entityStrJson : patientsFromStrArray){
            fstreamOut.write((entityStrJson+"\n").getBytes());
        }
        fstreamOut.close();
    }
    // <==================== al consultar lanzar progressBar =========================>
    // if disease 1 == anemia
    // else if disease 2 == diabetes
    // else if disease 3 == decease
    public void LaunchProgressBar(View view, int disease) {
        progressBar = new ProgressDialog(view.getContext());
        progressBar.setCancelable(false);
        progressBar.setMessage("Realizando consulta...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;

        new Thread(() -> {
            while (progressBarStatus < tiempoEsperaConsulta) { // indica los segundos para esperar
                progressBarStatus+= 1;
                progressBarbHandler.post(new Runnable() {
                    public void run() {
                        progressBar.setProgress(progressBarStatus);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // getting or not an answer from Orion
            try {
                receiveEntityFromOrion();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // setting updated values
                        switch(disease) {
                            case 1: // anemia
                                latestHasAnemia = patient.getAnemia().getHasAnaemia();
                                setImageSemaphore(latestHasAnemia, latestHasDiabetes, latestHasDecease);
                                setConsultAnswerOnAnemiaViews();
                                if (patient.getAnemia().getHasAnaemia()==-1){
                                    Toast.makeText(requireActivity(), "tiempo de espera muy largo, intente nuevamente", Toast.LENGTH_LONG).show();
                                }
                                break;
                            case 2: // diabetes
                                latestHasDiabetes = patient.getDiabetes().getHasDiabetes();
                                setImageSemaphore(latestHasAnemia, latestHasDiabetes, latestHasDecease);
                                setConsultAnswerOnDiabetesViews();
                                if (patient.getDiabetes().getHasDiabetes()==-1) {
                                    Toast.makeText(requireActivity(), "tiempo de espera muy largo, intente nuevamente", Toast.LENGTH_LONG).show();
                                }
                                break;
                            case 3: // decease
                                latestHasDecease = patient.getFallaCardiaca().getHasDeceased();
                                setImageSemaphore(latestHasAnemia, latestHasDiabetes, latestHasDecease);
                                setConsultAnswerOnDeceaseViews();
                                if (patient.getFallaCardiaca().getHasDeceased()==-1) {
                                    Toast.makeText(requireActivity(), "tiempo de espera muy largo, intente nuevamente", Toast.LENGTH_LONG).show();
                                }
                                break;
                        }
                    }
                });
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            progressBar.dismiss();
        }).start();
    }


    public void setImageSemaphore(int hasAnemia, int hasDiabetes, int hasDecease){
        switch(String.valueOf(hasAnemia)+
                String.valueOf(hasDiabetes)+
                String.valueOf(hasDecease))
        {
            case "011":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_011);
                break;
            case "01-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_01_1);
                break;
            case "010":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_010);
                break;
            case "0-11":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_0_11);
                break;
            case "0-1-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_0_1_1);
                break;
            case "0-10":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_0_10);
                break;
            case "001":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_001);
                break;
            case "00-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_00_1);
                break;
            case "000":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_000);
                break;


            case "1-1-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_1_1_1);
                break;
            case "1-10":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_1_10);
                break;
            case "1-11":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_1_11);
                break;
            case "10-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_10_1);
                break;
            case "11-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_11_1);
                break;
            case "100":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_100);
                break;
            case "101":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_101);
                break;
            case "110":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_110);
                break;
            case "111":
                imageViewSemaphore.setImageResource(R.drawable.semaphore_111);
                break;


            case "-1-1-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__1_1_1);
                break;
            case "-1-10":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__1_10);
                break;
            case "-1-11":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__1_11);
                break;
            case "-10-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__10_1);
                break;
            case "-11-1":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__11_1);
                break;
            case "-100":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__100);
                break;
            case "-101":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__101);
                break;
            case "-110":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__110);
                break;
            case "-111":
                imageViewSemaphore.setImageResource(R.drawable.semaphore__111);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}