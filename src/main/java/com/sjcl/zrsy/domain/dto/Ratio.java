package com.sjcl.zrsy.domain.dto;

public class Ratio {
    private String category;

    private int count;

    public Ratio(String category, int count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public int getCount() {
        return count;
    }
}
