package com.sjcl.zrsy.domain.dto;

import java.util.List;

public class VarietyRatio {
    private List<Ratio> breedSpot;
    private List<Ratio> breedGlobal;
    private Ratio genderSpot;
    private Ratio genderGlobal;

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

    public Ratio getGenderSpot() {
        return genderSpot;
    }

    public void setGenderSpot(Ratio genderSpot) {
        this.genderSpot = genderSpot;
    }

    public Ratio getGenderGlobal() {
        return genderGlobal;
    }

    public void setGenderGlobal(Ratio genderGlobal) {
        this.genderGlobal = genderGlobal;
    }
}
