package com.example.prediccionmedicaj.ui.createPatient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prediccionmedicaj.OperationOverPatient.anemiaInfoRegistroFragment;
import com.example.prediccionmedicaj.OperationOverPatient.diabetesInfoRegistroFragment;
import com.example.prediccionmedicaj.OperationOverPatient.fallaCardiacaInfoRegistroFragment;
import com.example.prediccionmedicaj.OperationOverPatient.generalInfoRegistroFragment;
import com.example.prediccionmedicaj.R;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelA;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelD;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelFC;
import com.example.prediccionmedicaj.clases.ViewModels.SharedViewModelGI;
//import com.example.prediccionmedicaj.databinding.FragmentHistorialConsultasBinding;
import com.example.prediccionmedicaj.objects.Anemia;
import com.example.prediccionmedicaj.objects.Diabetes;
import com.example.prediccionmedicaj.objects.FallaCardiaca;
import com.example.prediccionmedicaj.objects.GeneralInfo;
import com.example.prediccionmedicaj.objects.Paciente;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import lombok.SneakyThrows;

public class CreatePatientFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private anemiaInfoRegistroFragment AnemiaInfoRegistroFragment;
    private diabetesInfoRegistroFragment DiabetesInfoRegistroFragment;
    private generalInfoRegistroFragment GeneralInfoRegistroFragment;
    private fallaCardiacaInfoRegistroFragment FallaCardiacaInfoRegistroFragment;
    private final String ANEMIA_FRAGMENT_TAG = "myfragmenttaganemia";
    private final String GENERALINFO_FRAGMENT_TAG = "myfragmenttaggeneralinfo";
    private final String FALLACARDIACA_FRAGMENT_TAG = "myfragmenttagfallacardiaca";
    private final String DIABETES_FRAGMENT_TAG = "myfragmenttagdiabetes";
    private TabLayout registroInfoPacientetabLayout;
    private Paciente paciente;
    private FallaCardiaca fallaCardiaca;
    private GeneralInfo generalInfo;
    private Anemia anemia;
    private Diabetes diabetes;
    private Button btnImportPatientImg, btnRegisterPatient;
    private ImageView  imgViewPatientProfilePic;
    private JSONObject pacienteJSON;
    private String[] usersPhotosNames;
    private int limitPicNumber = 0 , userprofilePic;
    private String userProfilePicNameSTR="";
    private final String orionRed = "200.126.14.229", // "192.168.100.7" (mine)// "200.126.14.229" (ESPOL) //
            orionEntityPort = ":9090",
            orionEntityPath = "/v2/entities",
            orionAnaemiaSubscriptionPort = ":9090",
            orionDiabetesSubscriptionPort = ":9090",
            orionDeceaseSubscriptionPort = ":9090",
            orionSubscriptionPath = "/v2/subscriptions";
    private CreatePatientFragment binding;


    public CreatePatientFragment() {
        // Required empty public constructor
    }

    public static CreatePatientFragment newInstance(String param1, String param2) {
        CreatePatientFragment fragment = new CreatePatientFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_patient, container, false);
        usersPhotosNames = getResources().getStringArray(R.array.usersPhotosNames);
        paciente = new Paciente();
        imgViewPatientProfilePic = view.findViewById(R.id.imgViewPatientProfilePic);
        btnImportPatientImg = (Button) view.findViewById(R.id.btnImportPatientImg);
        btnRegisterPatient = (Button) view.findViewById(R.id.btnRegisterPatient);
        btnImportPatientImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                if (limitPicNumber==23) limitPicNumber = 0;
                userProfilePicNameSTR = usersPhotosNames[limitPicNumber];
                userprofilePic = requireContext().getResources().getIdentifier(
                        usersPhotosNames[limitPicNumber] ,
                        "drawable",
                        requireContext().getPackageName());
                imgViewPatientProfilePic.setImageResource(userprofilePic);
                // updated limitPicNumber value, then the next time the button is clicked another pic is showed
                limitPicNumber+=1;
            }
        });
        btnRegisterPatient.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                createPatient();
                if (paciente.getAnemia()!=null &&
                        paciente.getDiabetes()!=null &&
                        paciente.getFallaCardiaca()!=null &&
                        paciente.getGeneralInfo()!=null)
                {
                    try {
                        registerPatientToOrion();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    paciente.getGeneralInfo().setUserprofilePic(userProfilePicNameSTR);

                    storePatientLocally();
                    Navigation.findNavController(v).navigate(R.id.action_createPatientFragment_to_nav_home);
                }
            }
        });
        // Asociar tablayout
        registroInfoPacientetabLayout = (TabLayout) view.findViewById(R.id.patientParametersTabLayout); // get the reference of TabLayout
        TabLayout.Tab generalInfoRegistroTab = registroInfoPacientetabLayout.newTab(); // Create a new Tab names
        generalInfoRegistroTab.setText("General"); // set the Text for the first Tab
        registroInfoPacientetabLayout.addTab(generalInfoRegistroTab,0,true); // add  the tab at specified position in the TabLayout and makes it selectable
        TabLayout.Tab fallaCardiacaInfoRegistroTab = registroInfoPacientetabLayout.newTab(); // Create a new Tab names
        fallaCardiacaInfoRegistroTab.setText("Falla cardiaca"); // set the Text for the first Tab
        registroInfoPacientetabLayout.addTab(fallaCardiacaInfoRegistroTab,1);
        TabLayout.Tab anemiaInfoRegistroTab = registroInfoPacientetabLayout.newTab(); // Create a new Tab names
        anemiaInfoRegistroTab.setText("Anemia"); // set the Text for the first Tab
        registroInfoPacientetabLayout.addTab(anemiaInfoRegistroTab,2);
        TabLayout.Tab diabetesInfoRegistroTab = registroInfoPacientetabLayout.newTab(); // Create a new Tab names
        diabetesInfoRegistroTab.setText("Diabetes"); // set the Text for the first Tab
        registroInfoPacientetabLayout.addTab(diabetesInfoRegistroTab,3);
        launchFirstTab(savedInstanceState);
        registroInfoPacientetabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        if (savedInstanceState != null) {
                            GeneralInfoRegistroFragment = (generalInfoRegistroFragment)
                                    getParentFragmentManager().findFragmentByTag(ANEMIA_FRAGMENT_TAG);
                        } else if (GeneralInfoRegistroFragment == null) {
                            GeneralInfoRegistroFragment = new generalInfoRegistroFragment();
                        }
                        if (!GeneralInfoRegistroFragment.isInLayout()) {
                            getParentFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.registerInfoPatientFrameLayout, GeneralInfoRegistroFragment, GENERALINFO_FRAGMENT_TAG)
                                    .commit();
                            generalInfoFromFragment();
                        }
                        break;
                    case 1:
                        if (savedInstanceState != null) {
                            FallaCardiacaInfoRegistroFragment = (fallaCardiacaInfoRegistroFragment)
                                    getParentFragmentManager().findFragmentByTag(ANEMIA_FRAGMENT_TAG);
                        } else if (FallaCardiacaInfoRegistroFragment == null) {
                            FallaCardiacaInfoRegistroFragment = new fallaCardiacaInfoRegistroFragment();
                        }
                        if (!FallaCardiacaInfoRegistroFragment.isInLayout()) {
                            getParentFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.registerInfoPatientFrameLayout, FallaCardiacaInfoRegistroFragment, FALLACARDIACA_FRAGMENT_TAG)
                                    .commit();
                            fallaCardiacaFromFragment();
                        }
                        break;
                    case 2:
                        if (savedInstanceState != null) {
                            AnemiaInfoRegistroFragment = (anemiaInfoRegistroFragment)
                                    getParentFragmentManager().findFragmentByTag(ANEMIA_FRAGMENT_TAG);
                        } else if (AnemiaInfoRegistroFragment == null) {
                            AnemiaInfoRegistroFragment = new anemiaInfoRegistroFragment();
                        }
                        if (!AnemiaInfoRegistroFragment.isInLayout()) {
                            getParentFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.registerInfoPatientFrameLayout, AnemiaInfoRegistroFragment, ANEMIA_FRAGMENT_TAG)
                                    .commit();
                            anemiaFromFragment();
                        }
                        break;
                    case 3:
                        if (savedInstanceState != null) {
                            DiabetesInfoRegistroFragment = (diabetesInfoRegistroFragment)
                                    getParentFragmentManager().findFragmentByTag(ANEMIA_FRAGMENT_TAG);
                        } else if (DiabetesInfoRegistroFragment == null) {
                            DiabetesInfoRegistroFragment = new diabetesInfoRegistroFragment();
                        }
                        if (!DiabetesInfoRegistroFragment.isInLayout()) {
                            getParentFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.registerInfoPatientFrameLayout, DiabetesInfoRegistroFragment, DIABETES_FRAGMENT_TAG)
                                    .commit();
                            diabetesFromFragment();
                        }
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    void launchFirstTab( Bundle savedInstanceState){
        if (savedInstanceState != null) {
            GeneralInfoRegistroFragment = (generalInfoRegistroFragment)
                    getParentFragmentManager().findFragmentByTag(ANEMIA_FRAGMENT_TAG);
        } else if (GeneralInfoRegistroFragment == null) {
            GeneralInfoRegistroFragment = new generalInfoRegistroFragment();
        }
        if (!GeneralInfoRegistroFragment.isInLayout()) {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.registerInfoPatientFrameLayout, GeneralInfoRegistroFragment, GENERALINFO_FRAGMENT_TAG)
                    .commit();
            generalInfoFromFragment();
        }
    }

    public Anemia anemiaFromFragment() {
        anemia =  new Anemia();
        final SharedViewModelA model = ViewModelProviders.of(requireActivity()).get(SharedViewModelA.class);
        model.getMessage().observe(getViewLifecycleOwner(), object -> {
            assert object != null;
            anemia = (Anemia) object;
        });
        return anemia;
    }

    public Diabetes diabetesFromFragment() {
        diabetes = new Diabetes();
        final SharedViewModelD model = ViewModelProviders.of(requireActivity()).get(SharedViewModelD.class);
        model.getMessage().observe(getViewLifecycleOwner(), object -> {
            assert object != null;
            diabetes = (Diabetes) object;
        });
        return diabetes;
    }

    public FallaCardiaca fallaCardiacaFromFragment() {
        fallaCardiaca = new FallaCardiaca();
        final SharedViewModelFC model = ViewModelProviders.of(requireActivity()).get(SharedViewModelFC.class);
        model.getMessage().observe(getViewLifecycleOwner(), object -> {
            assert object != null;
            fallaCardiaca = (FallaCardiaca) object;
        });
        return fallaCardiaca;
    }

    public GeneralInfo generalInfoFromFragment() {
        generalInfo = new GeneralInfo();
        final SharedViewModelGI model = ViewModelProviders.of(requireActivity()).get(SharedViewModelGI.class);
        model.getMessage().observe(getViewLifecycleOwner(), object -> {
            assert object != null;
            generalInfo = (GeneralInfo) object;
        });
        return generalInfo;
    }

    public void createPatient(){
        paciente.setGeneralInfo(generalInfo);
        paciente.setFallaCardiaca(fallaCardiaca);
        paciente.setAnemia(anemia);
        paciente.setDiabetes(diabetes);
        paciente.getDiabetes().setHasDiabetes(-1);
        paciente.getDiabetes().setCalculateDiabetes(0);
        paciente.getFallaCardiaca().setHasDeceased(-1);
        paciente.getFallaCardiaca().setCalculateDeceased(0);
        paciente.getAnemia().setHasAnaemia(-1);
        paciente.getAnemia().setCalculateAnaemia(0);
    }

    public void registerPatientToOrion() throws IOException, JSONException {
        sendEntityToOrion(paciente.getJSONObjectEntity(), paciente);
    }

    public void sendEntityToOrion(JSONObject JSONpaciente, Paciente paciente) throws IOException, JSONException {
        String JSONpacienteToString = JSONpaciente.toString();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = new URL("http://"+orionRed+orionEntityPort+orionEntityPath); // 127.0.0.1 // 172.17.0.1 // 192.168.100.8:1026 // 10.0.2.2 (localHost of pc host, not emulator localHost) // ( 192.168.100.2 VM localhost)
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        byte[] out = JSONpacienteToString.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println("paciente ORION http.getResponseCode: "+ http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        subscribeEntityToOrion();
    }
    public void subscribeEntityToOrion() throws IOException {
        diabetesSubscription();
        anemiaSubscription();
        fallaCardiacaSubscription();
    }

    public void diabetesSubscription() throws IOException {
        String JSONpacienteToString = paciente.getSubscriptionDiabetesJ().toString();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = new URL("http://"+orionRed+orionDiabetesSubscriptionPort+orionSubscriptionPath); // 127.0.0.1 // 172.17.0.1 // 192.168.100.8:1026 // 10.0.2.2 (localHost of pc host, not emulator localHost) // ( 192.168.100.2 VM localhost)
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        byte[] out = JSONpacienteToString.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println("subscribe to ORION http.getResponseCode: "+ http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    public void anemiaSubscription() throws IOException {
        String JSONpacienteToString = paciente.getSubscriptionAnemiaJ().toString();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = new URL("http://"+orionRed+orionAnaemiaSubscriptionPort+orionSubscriptionPath); // 127.0.0.1 // 172.17.0.1 // 192.168.100.8:1026 // 10.0.2.2 (localHost of pc host, not emulator localHost) // ( 192.168.100.2 VM localhost)
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        byte[] out = JSONpacienteToString.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println("subscribe to ORION http.getResponseCode: "+ http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    public void fallaCardiacaSubscription() throws IOException {
        String JSONpacienteToString = paciente.getSubscriptionFallaCardiacaJ().toString();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();// it allows to make the internet request
        StrictMode.setThreadPolicy(policy);
        URL url = new URL("http://"+orionRed+orionDeceaseSubscriptionPort+orionSubscriptionPath); // 127.0.0.1 // 172.17.0.1 // 192.168.100.8:1026 //
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        byte[] out = JSONpacienteToString.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println("subscribe to ORION http.getResponseCode: "+ http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    public void storePatientLocally(){
        try {
            FileOutputStream fstream = requireActivity().openFileOutput("pacientes", Context.MODE_APPEND);
            String JSONpacienteStr = paciente.getJsonFullObject().toString()+"\n";
            fstream.write(JSONpacienteStr.getBytes());
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}