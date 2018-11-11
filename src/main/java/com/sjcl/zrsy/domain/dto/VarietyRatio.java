package com.sjcl.zrsy.domain.dto;

import java.util.List;

public class VarietyRatio {
    private List<Ratio> breedSpot;
    private List<Ratio> breedGlobal;
    private List<Ratio> genderSpot;
    private List<Ratio> genderGlobal;

    public List<Ratio> getBreedSpot() {
        return breedSpot;
    }

    public void setBreedSpot(List<Ratio> breedSpot) {
        this.breedSpot = breedSpot;
    }

    public List<Ratio> getBreedGlobal() {
        return breedGlobal;
    }

    public void setBreedGlobal(List<Ratio> breedGlobal) {
        this.breedGlobal = breedGlobal;
    }

    public List<Ratio> getGenderSpot() {
        return genderSpot;
    }

    public void setGenderSpot(List<Ratio> genderSpot) {
        this.genderSpot = genderSpot;
    }

    public List<Ratio> getGenderGlobal() {
        return genderGlobal;
    }

    public void setGenderGlobal(List<Ratio> genderGlobal) {
        this.genderGlobal = genderGlobal;
    }
}
