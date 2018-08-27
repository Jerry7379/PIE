package com.sjcl.zrsy.domain;
//运输数据
public class LogisticsWork {
    private String carid;
    private String temperature;
    private String humidity;
    private String location;
    private String time;
    private String co2;
    private String id;

    public LogisticsWork(String carid, String temperature, String humidity, String location, String time, String co2, String id) {
        this.carid = carid;
        this.temperature = temperature;
        this.humidity = humidity;
        this.location = location;
        this.time = time;
        this.co2 = co2;
        this.id = id;
    }

    public LogisticsWork() { }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
