package com.example.prediccionmedicaj.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



public class GeneralInfo {
    private String nameNoJson;
    private String type;
    private int id;
    private int gender;
    private int age;
    private int pregnancies;
    private String userprofilePic;

    public GeneralInfo() {
    }

    public GeneralInfo(String nameNoJson, String type, int id, int gender, int age, int pregnancies, String userprofilePic) {
        this.nameNoJson = nameNoJson;
        this.type = type;
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.pregnancies = pregnancies;
        this.userprofilePic = userprofilePic;
    }

    public String getNameNoJson() {
        return nameNoJson;
    }

    public void setNameNoJson(String nameNoJson) {
        this.nameNoJson = nameNoJson;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPregnancies() {
        return pregnancies;
    }

    public void setPregnancies(int pregnancies) {
        this.pregnancies = pregnancies;
    }

    public String getUserprofilePic() {
        return userprofilePic;
    }

    public void setUserprofilePic(String userprofilePic) {
        this.userprofilePic = userprofilePic;
    }

    @Override
    public String toString() {
        return "GeneralInfo{" +
                "nameNoJson='" + nameNoJson + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", gender=" + gender +
                ", age=" + age +
                ", pregnancies=" + pregnancies +
                ", userprofilePic='" + userprofilePic + '\'' +
                '}';
    }
}
