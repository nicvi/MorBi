package com.example.prediccionmedicaj.clases;

import androidx.annotation.NonNull;

import com.example.prediccionmedicaj.objects.Anemia;
import com.example.prediccionmedicaj.objects.Diabetes;
import com.example.prediccionmedicaj.objects.FallaCardiaca;
import com.example.prediccionmedicaj.objects.GeneralInfo;
import com.example.prediccionmedicaj.objects.Paciente;
import org.json.JSONException;
import org.json.JSONObject;

public class StringToPatientEntyty {
    private String stringJSON;

    public StringToPatientEntyty() {
    }

    public StringToPatientEntyty(String stringJSON) {
        this.stringJSON = stringJSON;
    }

    public Paciente convertToPatient() throws JSONException {
        // Get the JSON object
        JSONObject jsonPatient = new JSONObject(stringJSON);
        // get the patient object
        Paciente patient = new Paciente();
        GeneralInfo generalInfo = new GeneralInfo();
        Anemia anemia = new Anemia();
        Diabetes diabetes = new Diabetes();
        FallaCardiaca fallaCardiaca = new FallaCardiaca();
        // generalInfo
        generalInfo.setNameNoJson((String) jsonPatient.get("patientName"));
        generalInfo.setUserprofilePic(jsonPatient.getString("userprofilePic"));
        generalInfo.setType((String) jsonPatient.get("type"));
        generalInfo.setId(Integer.parseInt(((String) jsonPatient.get("id")).split("_")[1])); // cambiar el valor de ID a solo string
        generalInfo.setGender(Integer.parseInt((String) jsonPatient.getJSONObject("gender").getString("value")));
        generalInfo.setAge(jsonPatient.getJSONObject("age").getInt("value"));
        generalInfo.setPregnancies(jsonPatient.getJSONObject("pregnancies").getInt("value"));
        // anemia
        anemia.setHemoglobin(Float.parseFloat( jsonPatient.getJSONObject("hemoglobin").getString("value")));
        anemia.setMch(Float.parseFloat( jsonPatient.getJSONObject("mch").getString("value")));
        anemia.setMchc(Float.parseFloat(jsonPatient.getJSONObject("mchc").getString("value")));
        anemia.setMcv(Float.parseFloat(jsonPatient.getJSONObject("mcv").getString("value")));
        anemia.setCalculateAnaemia( jsonPatient.getJSONObject("calculateAnaemia").getInt("value"));
        anemia.setHasAnaemia( jsonPatient.getJSONObject("hasAnaemia").getInt("value"));
        // diabetes
        diabetes.setGlucose(jsonPatient.getJSONObject("glucose").getInt("value"));
        diabetes.setBloodPressure(jsonPatient.getJSONObject("bloodPressure").getInt("value"));
        diabetes.setSkinThickness(jsonPatient.getJSONObject("skinThickness").getInt("value"));
        diabetes.setInsulin(jsonPatient.getJSONObject("insulin").getInt("value"));
        diabetes.setBmi(Float.parseFloat(jsonPatient.getJSONObject("bmi").getString("value")));
        diabetes.setDiabetesPF(Float.parseFloat(jsonPatient.getJSONObject("diabetesPF").getString("value")));
        diabetes.setSleepDuration(jsonPatient.getJSONObject("sleepDuration").getInt("value"));
        diabetes.setCalculateDiabetes(jsonPatient.getJSONObject("calculateDiabetes").getInt("value"));
        diabetes.setHasDiabetes(jsonPatient.getJSONObject("hasDiabetes").getInt("value"));
        // fallaCardiaca
        fallaCardiaca.setCreatinePP(jsonPatient.getJSONObject("creatinePP").getInt("value"));
        fallaCardiaca.setEjecFrac(jsonPatient.getJSONObject("ejecFrac").getInt("value"));
        fallaCardiaca.setHighBloodP(jsonPatient.getJSONObject("highBloodP").getInt("value"));
        fallaCardiaca.setPlatelets(jsonPatient.getJSONObject("platelets").getInt("value"));
        fallaCardiaca.setSerumCreatine(Float.parseFloat(jsonPatient.getJSONObject("serumCreatinine").getString("value")));
        fallaCardiaca.setSerumSodium(jsonPatient.getJSONObject("serumSodium").getInt("value"));
        fallaCardiaca.setSmoking(jsonPatient.getJSONObject("smoking").getInt("value"));
        fallaCardiaca.setTime(jsonPatient.getJSONObject("time").getInt("value"));
        fallaCardiaca.setCalculateDeceased(jsonPatient.getJSONObject("calculateDeceased").getInt("value"));
        fallaCardiaca.setHasDeceased(jsonPatient.getJSONObject("hasDeceased").getInt("value"));
        // patient
        patient.setGeneralInfo(generalInfo);
        patient.setDiabetes(diabetes);
        patient.setAnemia(anemia);
        patient.setFallaCardiaca(fallaCardiaca);
        return patient;
    }

    // Useful when i get the entity from Orion, the entity from Orion does not have the patient name on it.
    public Paciente convertToPatientNoName() throws JSONException {
        // Get the JSON object
        JSONObject jsonPatient = new JSONObject(stringJSON);
        // get the patient object
        Paciente patient = new Paciente();
        GeneralInfo generalInfo = new GeneralInfo();
        Anemia anemia = new Anemia();
        Diabetes diabetes = new Diabetes();
        FallaCardiaca fallaCardiaca = new FallaCardiaca();
        // generalInfo
        generalInfo.setType((String) jsonPatient.get("type"));
        generalInfo.setId(Integer.parseInt(((String) jsonPatient.get("id")).split("_")[1])); // cambiar el valor de ID a solo string
        generalInfo.setGender(Integer.parseInt((String) jsonPatient.getJSONObject("gender").getString("value")));
        generalInfo.setAge(jsonPatient.getJSONObject("age").getInt("value"));
        generalInfo.setPregnancies(jsonPatient.getJSONObject("pregnancies").getInt("value"));
        // anemia
        anemia.setHemoglobin(Float.parseFloat( jsonPatient.getJSONObject("hemoglobin").getString("value")));
        anemia.setMch(Float.parseFloat( jsonPatient.getJSONObject("mch").getString("value")));
        anemia.setMchc(Float.parseFloat(jsonPatient.getJSONObject("mchc").getString("value")));
        anemia.setMcv(Float.parseFloat(jsonPatient.getJSONObject("mcv").getString("value")));
        anemia.setCalculateAnaemia( jsonPatient.getJSONObject("calculateAnaemia").getInt("value"));
        anemia.setHasAnaemia( jsonPatient.getJSONObject("hasAnaemia").getInt("value"));
        // diabetes
        diabetes.setGlucose(jsonPatient.getJSONObject("glucose").getInt("value"));
        diabetes.setBloodPressure(jsonPatient.getJSONObject("bloodPressure").getInt("value"));
        diabetes.setSkinThickness(jsonPatient.getJSONObject("skinThickness").getInt("value"));
        diabetes.setInsulin(jsonPatient.getJSONObject("insulin").getInt("value"));
        diabetes.setBmi(Float.parseFloat(jsonPatient.getJSONObject("bmi").getString("value")));
        diabetes.setDiabetesPF(Float.parseFloat(jsonPatient.getJSONObject("diabetesPF").getString("value")));
        diabetes.setSleepDuration(jsonPatient.getJSONObject("sleepDuration").getInt("value"));
        diabetes.setCalculateDiabetes(jsonPatient.getJSONObject("calculateDiabetes").getInt("value"));
        diabetes.setHasDiabetes(jsonPatient.getJSONObject("hasDiabetes").getInt("value"));
        // fallaCardiaca
        fallaCardiaca.setCreatinePP(jsonPatient.getJSONObject("creatinePP").getInt("value"));
        fallaCardiaca.setEjecFrac(jsonPatient.getJSONObject("ejecFrac").getInt("value"));
        fallaCardiaca.setHighBloodP(jsonPatient.getJSONObject("highBloodP").getInt("value"));
        fallaCardiaca.setPlatelets(jsonPatient.getJSONObject("platelets").getInt("value"));
        fallaCardiaca.setSerumCreatine(Float.parseFloat(jsonPatient.getJSONObject("serumCreatinine").getString("value")));
        fallaCardiaca.setSerumSodium(jsonPatient.getJSONObject("serumSodium").getInt("value"));
        fallaCardiaca.setSmoking(jsonPatient.getJSONObject("smoking").getInt("value"));
        fallaCardiaca.setTime(jsonPatient.getJSONObject("time").getInt("value"));
        fallaCardiaca.setCalculateDeceased(jsonPatient.getJSONObject("calculateDeceased").getInt("value"));
        fallaCardiaca.setHasDeceased(jsonPatient.getJSONObject("hasDeceased").getInt("value"));
        // patient
        patient.setGeneralInfo(generalInfo);
        patient.setDiabetes(diabetes);
        patient.setAnemia(anemia);
        patient.setFallaCardiaca(fallaCardiaca);
        return patient;
    }

    public String getStringJSON() {
        return stringJSON;
    }

    public void setStringJSON(String stringJSON) {
        this.stringJSON = stringJSON;
    }

    @NonNull
    @Override
    public String toString() {
        return "StringToPatientEntyty{" +
                "stringJSON='" + stringJSON + '\'' +
                '}';
    }
}