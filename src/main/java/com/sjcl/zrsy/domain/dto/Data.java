package com.sjcl.zrsy.domain.dto;

public class Data {
    private int totalCount;
    private int outBarCount;
    private double outBarAvgWeight;

    public Data(){}

    public Data(int totalCount, int outBarCount, int outBarAvgWeight) {
        this.totalCount = totalCount;
        this.outBarCount = outBarCount;
        this.outBarAvgWeight = outBarAvgWeight;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getOutBarCount() {
        return outBarCount;
    }

    public void setOutBarCount(int outBarCount) {
        this.outBarCount = outBarCount;
    }

    public double getOutBarAvgWeight() {
        return outBarAvgWeight;
    }

    public void setOutBarAvgWeight(double outBarAvgWeight) {
        this.outBarAvgWeight = outBarAvgWeight;
    }
}
