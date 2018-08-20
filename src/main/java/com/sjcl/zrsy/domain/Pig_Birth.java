package com.sjcl.zrsy.domain;

public class Pig_Birth {
    private String id;  //猪id 13位
    private String farm_id;
    private String date;
    private String breed;
    private String gender;
    private String weight;

    public Pig_Birth(){
    }


    public Pig_Birth(String id, String farm_id, String date, String breed, String gender, String weight) {
        this.id = id;
        this.farm_id = farm_id;
        this.date = date;
        this.breed = breed;
        this.gender = gender;
        this.weight=weight;

    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public String getWeight() {
        return weight;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
