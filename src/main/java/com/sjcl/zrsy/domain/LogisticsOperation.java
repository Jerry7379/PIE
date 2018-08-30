package com.sjcl.zrsy.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import javax.xml.crypto.Data;

//运输数据
public class LogisticsOperation {
    @Length(min = 7,max = 7,message = "车牌号长度为7")
    private String carId;
    @Digits(integer = 99,fraction = 99,message = "温度格式错误")
    private double temperature;
    @Digits(integer = 99,fraction = 99,message = "湿度格式错误")
    private double humidity;

    private String location;

    private String time;
    @Digits(integer = 99,fraction = 99,message = "CO2格式错误")
    private double co2;
    @Length(min = 13,max = 13,message = "猪ID长度为13")
    private String  id;

    public LogisticsOperation(@Length(min = 7, max = 7, message = "车牌号长度为7") String carId, @Digits(integer = 99, fraction = 99, message = "温度格式错误") double temperature, @Digits(integer = 99, fraction = 99, message = "湿度格式错误") double humidity, String location, String time, @Digits(integer = 99, fraction = 99, message = "CO2格式错误") double co2, @Length(min = 13, max = 13, message = "猪ID长度为13") String id) {
        this.carId = carId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.location = location;
        this.time = time;
        this.co2 = co2;
        this.id = id;
    }

    public LogisticsOperation() {
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
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

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
