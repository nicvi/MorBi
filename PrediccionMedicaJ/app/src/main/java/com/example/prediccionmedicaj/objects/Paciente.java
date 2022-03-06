package com.example.prediccionmedicaj.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



public class Paciente {
    private GeneralInfo generalInfo;
    private FallaCardiaca fallaCardiaca;
    private Anemia anemia;
    private Diabetes diabetes;

    public Paciente() {
    }

    public Paciente(GeneralInfo generalInfo, FallaCardiaca fallaCardiaca, Anemia anemia, Diabetes diabetes) {
        this.generalInfo = generalInfo;
        this.fallaCardiaca = fallaCardiaca;
        this.anemia = anemia;
        this.diabetes = diabetes;
    }

    public JSONObject getJSONObjectEntity(){
        JSONObject pacienteJ = new JSONObject();
        try {
            // General info Orion
            pacienteJ.put("id", "p_" + generalInfo.getId());
            pacienteJ.put("type", "Paciente");

            // General info Paciente
            JSONObject cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getGender());
            cuerpo.put("type", "Integer");
            pacienteJ.put("gender", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getAge());
            cuerpo.put("type", "Integer");
            pacienteJ.put("age", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getPregnancies());
            cuerpo.put("type", "Integer");
            pacienteJ.put("pregnancies", cuerpo);

            // ====> Anemia
            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getHemoglobin());
            cuerpo.put("type", "Float");
            pacienteJ.put("hemoglobin", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMch());
            cuerpo.put("type", "Float");
            pacienteJ.put("mch", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMchc());
            cuerpo.put("type", "Float");
            pacienteJ.put("mchc", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMcv());
            cuerpo.put("type", "Float");
            pacienteJ.put("mcv", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getCalculateAnaemia());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateAnaemia", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getHasAnaemia());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasAnaemia", cuerpo);

            // ====> Diabetes
            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getGlucose());
            cuerpo.put("type", "Integer");
            pacienteJ.put("glucose", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getBloodPressure());
            cuerpo.put("type", "Integer");
            pacienteJ.put("bloodPressure", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getSkinThickness());
            cuerpo.put("type", "Integer");
            pacienteJ.put("skinThickness", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getInsulin());
            cuerpo.put("type", "Integer");
            pacienteJ.put("insulin", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getBmi());
            cuerpo.put("type", "Float");
            pacienteJ.put("bmi", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getDiabetesPF());
            cuerpo.put("type", "Float");
            pacienteJ.put("diabetesPF", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getCalorieIntake());
            cuerpo.put("type", "Float");
            pacienteJ.put("calorieIntake", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getExercise());
            cuerpo.put("type", "Integer");
            pacienteJ.put("exercise", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getSleepDuration());
            cuerpo.put("type", "Integer");
            pacienteJ.put("sleepDuration", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getCalculateDiabetes());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateDiabetes", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getHasDiabetes());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasDiabetes", cuerpo);

            // ====> Falla cardiaca
            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getCreatinePP());
            cuerpo.put("type", "Integer");
            pacienteJ.put("creatinePP", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getEjecFrac());
            cuerpo.put("type", "Integer");
            pacienteJ.put("ejecFrac", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getHighBloodP());
            cuerpo.put("type", "Integer");
            pacienteJ.put("highBloodP", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getPlatelets());
            cuerpo.put("type", "Integer");
            pacienteJ.put("platelets", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSerumCreatine());
            cuerpo.put("type", "Float");
            pacienteJ.put("serumCreatinine", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSerumSodium());
            cuerpo.put("type", "Integer");
            pacienteJ.put("serumSodium", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSmoking());
            cuerpo.put("type", "Integer");
            pacienteJ.put("smoking", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getTime());
            cuerpo.put("type", "Integer");
            pacienteJ.put("time", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getCalculateDeceased());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateDeceased", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getHasDeceased());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasDeceased", cuerpo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pacienteJ;
    }

    public JSONObject getJsonFullObject(){
        JSONObject pacienteJ = new JSONObject();
        try {
            // no entity info patient
            pacienteJ.put("patientName",  generalInfo.getNameNoJson());
            pacienteJ.put("userprofilePic",  generalInfo.getUserprofilePic());

            // General info Orion
            pacienteJ.put("id", "p_" + generalInfo.getId());
            pacienteJ.put("type", "Paciente");

            // General info Paciente
            JSONObject cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getGender());
            cuerpo.put("type", "Integer");
            pacienteJ.put("gender", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getAge());
            cuerpo.put("type", "Integer");
            pacienteJ.put("age", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getPregnancies());
            cuerpo.put("type", "Integer");
            pacienteJ.put("pregnancies", cuerpo);

            // ====> Anemia
            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getHemoglobin());
            cuerpo.put("type", "Float");
            pacienteJ.put("hemoglobin", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMch());
            cuerpo.put("type", "Float");
            pacienteJ.put("mch", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMchc());
            cuerpo.put("type", "Float");
            pacienteJ.put("mchc", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMcv());
            cuerpo.put("type", "Float");
            pacienteJ.put("mcv", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getCalculateAnaemia());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateAnaemia", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getHasAnaemia());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasAnaemia", cuerpo);

            // ====> Diabetes
            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getGlucose());
            cuerpo.put("type", "Integer");
            pacienteJ.put("glucose", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getBloodPressure());
            cuerpo.put("type", "Integer");
            pacienteJ.put("bloodPressure", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getSkinThickness());
            cuerpo.put("type", "Integer");
            pacienteJ.put("skinThickness", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getInsulin());
            cuerpo.put("type", "Integer");
            pacienteJ.put("insulin", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getBmi());
            cuerpo.put("type", "Float");
            pacienteJ.put("bmi", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getDiabetesPF());
            cuerpo.put("type", "Float");
            pacienteJ.put("diabetesPF", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getCalorieIntake());
            cuerpo.put("type", "Float");
            pacienteJ.put("calorieIntake", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getExercise());
            cuerpo.put("type", "Integer");
            pacienteJ.put("exercise", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getSleepDuration());
            cuerpo.put("type", "Integer");
            pacienteJ.put("sleepDuration", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getCalculateDiabetes());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateDiabetes", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getHasDiabetes());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasDiabetes", cuerpo);

            // ====> Falla cardiaca
            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getCreatinePP());
            cuerpo.put("type", "Integer");
            pacienteJ.put("creatinePP", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getEjecFrac());
            cuerpo.put("type", "Integer");
            pacienteJ.put("ejecFrac", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getHighBloodP());
            cuerpo.put("type", "Integer");
            pacienteJ.put("highBloodP", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getPlatelets());
            cuerpo.put("type", "Integer");
            pacienteJ.put("platelets", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSerumCreatine());
            cuerpo.put("type", "Float");
            pacienteJ.put("serumCreatinine", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSerumSodium());
            cuerpo.put("type", "Integer");
            pacienteJ.put("serumSodium", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSmoking());
            cuerpo.put("type", "Integer");
            pacienteJ.put("smoking", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getTime());
            cuerpo.put("type", "Integer");
            pacienteJ.put("time", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getCalculateDeceased());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateDeceased", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getHasDeceased());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasDeceased", cuerpo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pacienteJ;
    }

    // =========================================================================================

    public JSONObject getSubscriptionAnemiaJ(){
        JSONObject subscriptionJ = new JSONObject();
        JSONObject value1Level, value2Level;
        JSONArray listValues = new JSONArray();
        try {
            // - description
            subscriptionJ.put("description", "Suscripcion de anemia para monitorear al Paciente");
            // - subject
            value1Level = new JSONObject();
            // -- entities
            listValues = new  JSONArray();
            value2Level = new JSONObject();
            value2Level.put("id", "p_"+generalInfo.getId());
            value2Level.put("type", "Paciente");
            listValues.put(value2Level);
            value1Level.put("entities", listValues);
            // -- conditions
            value2Level = new JSONObject();
            listValues = new JSONArray();
            listValues.put("calculateAnaemia"); // value of Patient parameter.
            value2Level.put("attrs",listValues);
            value1Level.put("condition", value2Level);
            // -- expression
            value2Level = new JSONObject();
            value2Level.put("q","calculateAnemia:1");
            value1Level.put("expression", value2Level);
            subscriptionJ.put("subject", value1Level);
            // - notification
            value1Level = new JSONObject();
            // -- http
            value2Level = new JSONObject();
            value2Level.put("url","http://10.0.1.2:9002/notify" ); // url of Orion
            value1Level.put("http", value2Level);
            // -- attrs
            listValues = new JSONArray();
            //list.put("list");
            listValues.put("gender");
            listValues.put("age");
            listValues.put("hemoglobin");
            listValues.put("mch");
            listValues.put("mchc");
            listValues.put("mcv");
            value1Level.put("attrs", listValues);

            subscriptionJ.put("notification", value1Level);
            // - expires
            subscriptionJ.put("expires", "2040-01-01T14:00:00.00Z");
            // - throttling
            subscriptionJ.put("throttling", 5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subscriptionJ;
    }

    public JSONObject getSubscriptionDiabetesJ(){
        JSONObject subscriptionJ = new JSONObject();
        JSONObject value1Level, value2Level;
        JSONArray listValues = new JSONArray();
        try {
            // - description
            subscriptionJ.put("description", "Suscripcion de diabetes para monitorear al Paciente");
            // - subject
            value1Level = new JSONObject();
            // -- entities
            listValues = new  JSONArray();
            value2Level = new JSONObject();
            value2Level.put("id", "p_"+generalInfo.getId());
            value2Level.put("type", "Paciente");
            listValues.put(value2Level);
            value1Level.put("entities", listValues);
            // -- conditions
            value2Level = new JSONObject();
            listValues = new JSONArray();
            listValues.put("calculateDiabetes"); // value of Patient parameter.
            value2Level.put("attrs",listValues);
            value1Level.put("condition", value2Level);
            // -- expression
            value2Level = new JSONObject();
            value2Level.put("q","calculateDiabetes:1");
            value1Level.put("expression", value2Level);
            subscriptionJ.put("subject", value1Level);
            // - notification
            value1Level = new JSONObject();
            // -- http
            value2Level = new JSONObject();
            value2Level.put("url","http://10.0.1.3:9003/notify" ); // url of Orion
            value1Level.put("http", value2Level);
            // -- attrs
            listValues = new JSONArray();
            //list.put("list");
            listValues.put("gender");
            listValues.put("age");
            listValues.put("pregnancies");
            listValues.put("glucose");
            listValues.put("bloodPressure");
            listValues.put("skinThickness");
            listValues.put("insulin");
            listValues.put("bmi");
            listValues.put("diabetesPF");
            listValues.put("calorieIntake");
            listValues.put("exercise");
            listValues.put("sleepDuration");
            value1Level.put("attrs", listValues);
            subscriptionJ.put("notification", value1Level);
            // - expires
            subscriptionJ.put("expires", "2040-01-01T14:00:00.00Z");
            // - throttling
            subscriptionJ.put("throttling", 5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("JSON Subscription SubscriptionDiabetesJ: " + subscriptionJ);
        return subscriptionJ;
    }

    public JSONObject getSubscriptionFallaCardiacaJ(){
        JSONObject subscriptionJ = new JSONObject();
        JSONObject value1Level, value2Level;
        JSONArray listValues = new JSONArray();
        try {
            // - description
            subscriptionJ.put("description", "Suscripcion de deceso para monitorear al Paciente");// nesting
            // - subject
            value1Level = new JSONObject();
            // -- entities
            listValues = new  JSONArray();
            value2Level = new JSONObject();
            value2Level.put("id", "p_"+generalInfo.getId());
            value2Level.put("type", "Paciente");
            listValues.put(value2Level);
            value1Level.put("entities", listValues);
            // -- condition
            value2Level = new JSONObject();
            listValues = new JSONArray();
            listValues.put("calculateDeceased"); // value of Patient parameter.
            value2Level.put("attrs",listValues);
            value1Level.put("condition", value2Level);
            // -- expression
            value2Level = new JSONObject();
            value2Level.put("q","calculateDeceased:1");
            value1Level.put("expression", value2Level);
            subscriptionJ.put("subject", value1Level);// nesting
            // - notification
            value1Level = new JSONObject();
            // -- http
            value2Level = new JSONObject();
            value2Level.put("url","http://10.0.1.4:9004/notify" ); // url of Orion
            value1Level.put("http", value2Level);
            // -- attrs
            listValues = new JSONArray();
            listValues.put("gender");
            listValues.put("age");
            listValues.put("hasAnaemia");
            listValues.put("creatinePP");
            listValues.put("hasDiabetes");
            listValues.put("ejecFrac");
            listValues.put("highBloodP");
            listValues.put("platelets");
            listValues.put("serumCreatinine");
            listValues.put("serumSodium");
            listValues.put("smoking");
            listValues.put("time");
            value1Level.put("attrs", listValues);
            subscriptionJ.put("notification", value1Level);// nesting
            subscriptionJ.put("expires", "2040-01-01T14:00:00.00Z");// nesting
            subscriptionJ.put("throttling", 5);// nesting
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("JSON SubscriptionFallaCardiacaJ sent: " + subscriptionJ);
        return subscriptionJ;
    }

    // =================================================

    public JSONObject getUpdatedJsonPatientEntity(){
        JSONObject pacienteJ = new JSONObject();
        try {
            // General info Paciente
            JSONObject cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getGender());
            cuerpo.put("type", "Integer");
            pacienteJ.put("gender", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getAge());
            cuerpo.put("type", "Integer");
            pacienteJ.put("age", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", generalInfo.getPregnancies());
            cuerpo.put("type", "Integer");
            pacienteJ.put("pregnancies", cuerpo);

            // ====> Anemia
            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getHemoglobin());
            cuerpo.put("type", "Float");
            pacienteJ.put("hemoglobin", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMch());
            cuerpo.put("type", "Float");
            pacienteJ.put("mch", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMchc());
            cuerpo.put("type", "Float");
            pacienteJ.put("mchc", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getMcv());
            cuerpo.put("type", "Float");
            pacienteJ.put("mcv", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getCalculateAnaemia());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateAnaemia", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", anemia.getHasAnaemia());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasAnaemia", cuerpo);

            // ====> Diabetes
            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getGlucose());
            cuerpo.put("type", "Integer");
            pacienteJ.put("glucose", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getBloodPressure());
            cuerpo.put("type", "Integer");
            pacienteJ.put("bloodPressure", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getSkinThickness());
            cuerpo.put("type", "Integer");
            pacienteJ.put("skinThickness", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getInsulin());
            cuerpo.put("type", "Integer");
            pacienteJ.put("insulin", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getBmi());
            cuerpo.put("type", "Float");
            pacienteJ.put("bmi", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getDiabetesPF());
            cuerpo.put("type", "Float");
            pacienteJ.put("diabetesPF", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getCalorieIntake());
            cuerpo.put("type", "Float");
            pacienteJ.put("calorieIntake", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getExercise());
            cuerpo.put("type", "Integer");
            pacienteJ.put("exercise", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getSleepDuration());
            cuerpo.put("type", "Integer");
            pacienteJ.put("sleepDuration", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getCalculateDiabetes());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateDiabetes", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", diabetes.getHasDiabetes());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasDiabetes", cuerpo);

            // ====> Falla cardiaca
            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getCreatinePP());
            cuerpo.put("type", "Integer");
            pacienteJ.put("creatinePP", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getEjecFrac());
            cuerpo.put("type", "Integer");
            pacienteJ.put("ejecFrac", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getHighBloodP());
            cuerpo.put("type", "Integer");
            pacienteJ.put("highBloodP", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getPlatelets());
            cuerpo.put("type", "Integer");
            pacienteJ.put("platelets", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSerumCreatine());
            cuerpo.put("type", "Float");
            pacienteJ.put("serumCreatinine", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSerumSodium());
            cuerpo.put("type", "Integer");
            pacienteJ.put("serumSodium", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getSmoking());
            cuerpo.put("type", "Integer");
            pacienteJ.put("smoking", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getTime());
            cuerpo.put("type", "Integer");
            pacienteJ.put("time", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getCalculateDeceased());
            cuerpo.put("type", "Integer");
            pacienteJ.put("calculateDeceased", cuerpo);

            cuerpo = new JSONObject();
            cuerpo.put("value", fallaCardiaca.getHasDeceased());
            cuerpo.put("type", "Integer");
            pacienteJ.put("hasDeceased", cuerpo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("JSON Entity created: " + pacienteJ);
        return pacienteJ;
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public FallaCardiaca getFallaCardiaca() {
        return fallaCardiaca;
    }

    public void setFallaCardiaca(FallaCardiaca fallaCardiaca) {
        this.fallaCardiaca = fallaCardiaca;
    }

    public Anemia getAnemia() {
        return anemia;
    }

    public void setAnemia(Anemia anemia) {
        this.anemia = anemia;
    }

    public Diabetes getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Diabetes diabetes) {
        this.diabetes = diabetes;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "generalInfo=" + generalInfo +
                ", fallaCardiaca=" + fallaCardiaca +
                ", anemia=" + anemia +
                ", diabetes=" + diabetes +
                '}';
    }
}
