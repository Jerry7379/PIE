package com.sjcl.zrsy.domain;

public class Pig_Birth {
    private String id;
    private String farm_id;
    private String date;
    private String breed;
    private String gender;
    private double weight;

    public Pig_Birth(){
    }


    public Pig_Birth(String id, String farm_id, String date, String breed, String gender, double weight) {
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

    public double getWeight() {
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

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
