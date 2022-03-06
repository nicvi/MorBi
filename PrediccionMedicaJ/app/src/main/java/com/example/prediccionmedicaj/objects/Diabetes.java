package com.example.prediccionmedicaj.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



public class Diabetes {
    private int glucose;
    private int bloodPressure;
    private int skinThickness;
    private int insulin;
    private float bmi;
    private float diabetesPF;
    private float calorieIntake;
    private int exercise;
    private int sleepDuration;

    private int calculateDiabetes;
    private int hasDiabetes;

    public Diabetes() {
    }

    public Diabetes(int glucose, int bloodPressure, int skinThickness, int insulin, float bmi, float diabetesPF, float calorieIntake, int exercise, int sleepDuration, int calculateDiabetes, int hasDiabetes) {
        this.glucose = glucose;
        this.bloodPressure = bloodPressure;
        this.skinThickness = skinThickness;
        this.insulin = insulin;
        this.bmi = bmi;
        this.diabetesPF = diabetesPF;
        this.calorieIntake = calorieIntake;
        this.exercise = exercise;
        this.sleepDuration = sleepDuration;
        this.calculateDiabetes = calculateDiabetes;
        this.hasDiabetes = hasDiabetes;
    }

    public int getGlucose() {
        return glucose;
    }

    public void setGlucose(int glucose) {
        this.glucose = glucose;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getSkinThickness() {
        return skinThickness;
    }

    public void setSkinThickness(int skinThickness) {
        this.skinThickness = skinThickness;
    }

    public int getInsulin() {
        return insulin;
    }

    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public float getDiabetesPF() {
        return diabetesPF;
    }

    public void setDiabetesPF(float diabetesPF) {
        this.diabetesPF = diabetesPF;
    }

    public float getCalorieIntake() {
        return calorieIntake;
    }

    public void setCalorieIntake(float calorieIntake) {
        this.calorieIntake = calorieIntake;
    }

    public int getExercise() {
        return exercise;
    }

    public void setExercise(int exercise) {
        this.exercise = exercise;
    }

    public int getSleepDuration() {
        return sleepDuration;
    }

    public void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    public int getCalculateDiabetes() {
        return calculateDiabetes;
    }

    public void setCalculateDiabetes(int calculateDiabetes) {
        this.calculateDiabetes = calculateDiabetes;
    }

    public int getHasDiabetes() {
        return hasDiabetes;
    }

    public void setHasDiabetes(int hasDiabetes) {
        this.hasDiabetes = hasDiabetes;
    }

    @Override
    public String toString() {
        return "Diabetes{" +
                "glucose=" + glucose +
                ", bloodPressure=" + bloodPressure +
                ", skinThickness=" + skinThickness +
                ", insulin=" + insulin +
                ", bmi=" + bmi +
                ", diabetesPF=" + diabetesPF +
                ", calorieIntake=" + calorieIntake +
                ", exercise=" + exercise +
                ", sleepDuration=" + sleepDuration +
                ", calculateDiabetes=" + calculateDiabetes +
                ", hasDiabetes=" + hasDiabetes +
                '}';
    }
}
