package com.sjcl.zrsy.domain.dto;

public class CurrentWeekData {
    private int brith;
    private int outBar;
    private double outBarAvgWeight;

    public CurrentWeekData() {}

    public CurrentWeekData(int brith, int outBar, double outBarAvgWeight) {
        this.brith = brith;
        this.outBar = outBar;
        this.outBarAvgWeight = outBarAvgWeight;
    }

    public int getBrith() {
        return brith;
    }

    public void setBrith(int brith) {
        this.brith = brith;
    }

    public int getOutBar() {
        return outBar;
    }

    public void setOutBar(int outBar) {
        this.outBar = outBar;
    }

    public double getOutBarAvgWeight() {
        return outBarAvgWeight;
    }

    public void setOutBarAvgWeight(double outBarAvgWeight) {
        this.outBarAvgWeight = outBarAvgWeight;
    }
}
