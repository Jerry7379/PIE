package com.sjcl.zrsy.domain.dto;

public class Current {
    private AllData allData;
    private CurrentWeekData currentWeekData;

    public AllData getAllData() {
        return allData;
    }

    public void setAllData(AllData allData) {
        this.allData = allData;
    }

    public CurrentWeekData getCurrentWeekData() {
        return currentWeekData;
    }

    public void setCurrentWeekData(CurrentWeekData currentWeekData) {
        this.currentWeekData = currentWeekData;
    }
}
