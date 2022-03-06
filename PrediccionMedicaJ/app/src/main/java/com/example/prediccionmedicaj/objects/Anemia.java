package com.example.prediccionmedicaj.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Anemia {
    private float hemoglobin;
    private float mch;
    private float mchc;
    private float mcv;

    private int calculateAnaemia;
    private int hasAnaemia;

    public Anemia(float hemoglobin, float mch, float mchc, float mcv, int calculateAnaemia, int hasAnaemia) {
        this.hemoglobin = hemoglobin;
        this.mch = mch;
        this.mchc = mchc;
        this.mcv = mcv;
        this.calculateAnaemia = calculateAnaemia;
        this.hasAnaemia = hasAnaemia;
    }

    public Anemia() {
    }

    public float getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(float hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public float getMch() {
        return mch;
    }

    public void setMch(float mch) {
        this.mch = mch;
    }

    public float getMchc() {
        return mchc;
    }

    public void setMchc(float mchc) {
        this.mchc = mchc;
    }

    public float getMcv() {
        return mcv;
    }

    public void setMcv(float mcv) {
        this.mcv = mcv;
    }

    public int getCalculateAnaemia() {
        return calculateAnaemia;
    }

    public void setCalculateAnaemia(int calculateAnaemia) {
        this.calculateAnaemia = calculateAnaemia;
    }

    public int getHasAnaemia() {
        return hasAnaemia;
    }

    public void setHasAnaemia(int hasAnaemia) {
        this.hasAnaemia = hasAnaemia;
    }

    @Override
    public String toString() {
        return "Anemia{" +
                "hemoglobin=" + hemoglobin +
                ", mch=" + mch +
                ", mchc=" + mchc +
                ", mcv=" + mcv +
                ", calculateAnaemia=" + calculateAnaemia +
                ", hasAnaemia=" + hasAnaemia +
                '}';
    }
}
