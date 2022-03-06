package com.example.prediccionmedicaj.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



public class FallaCardiaca {
    private int creatinePP;
    private int ejecFrac;
    private int highBloodP;
    private int platelets;
    private float serumCreatine;
    private int serumSodium;
    private int smoking;
    private int time;

    private int calculateDeceased;
    private int hasDeceased;

    public FallaCardiaca() {
    }

    public FallaCardiaca(int creatinePP, int ejecFrac, int highBloodP, int platelets, float serumCreatine, int serumSodium, int smoking, int time, int calculateDeceased, int hasDeceased) {
        this.creatinePP = creatinePP;
        this.ejecFrac = ejecFrac;
        this.highBloodP = highBloodP;
        this.platelets = platelets;
        this.serumCreatine = serumCreatine;
        this.serumSodium = serumSodium;
        this.smoking = smoking;
        this.time = time;
        this.calculateDeceased = calculateDeceased;
        this.hasDeceased = hasDeceased;
    }

    public int getCreatinePP() {
        return creatinePP;
    }

    public void setCreatinePP(int creatinePP) {
        this.creatinePP = creatinePP;
    }

    public int getEjecFrac() {
        return ejecFrac;
    }

    public void setEjecFrac(int ejecFrac) {
        this.ejecFrac = ejecFrac;
    }

    public int getHighBloodP() {
        return highBloodP;
    }

    public void setHighBloodP(int highBloodP) {
        this.highBloodP = highBloodP;
    }

    public int getPlatelets() {
        return platelets;
    }

    public void setPlatelets(int platelets) {
        this.platelets = platelets;
    }

    public float getSerumCreatine() {
        return serumCreatine;
    }

    public void setSerumCreatine(float serumCreatine) {
        this.serumCreatine = serumCreatine;
    }

    public int getSerumSodium() {
        return serumSodium;
    }

    public void setSerumSodium(int serumSodium) {
        this.serumSodium = serumSodium;
    }

    public int getSmoking() {
        return smoking;
    }

    public void setSmoking(int smoking) {
        this.smoking = smoking;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCalculateDeceased() {
        return calculateDeceased;
    }

    public void setCalculateDeceased(int calculateDeceased) {
        this.calculateDeceased = calculateDeceased;
    }

    public int getHasDeceased() {
        return hasDeceased;
    }

    public void setHasDeceased(int hasDeceased) {
        this.hasDeceased = hasDeceased;
    }

    @Override
    public String toString() {
        return "FallaCardiaca{" +
                "creatinePP=" + creatinePP +
                ", ejecFrac=" + ejecFrac +
                ", highBloodP=" + highBloodP +
                ", platelets=" + platelets +
                ", serumCreatine=" + serumCreatine +
                ", serumSodium=" + serumSodium +
                ", smoking=" + smoking +
                ", time=" + time +
                ", calculateDeceased=" + calculateDeceased +
                ", hasDeceased=" + hasDeceased +
                '}';
    }
}
