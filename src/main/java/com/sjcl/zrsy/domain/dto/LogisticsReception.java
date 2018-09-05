package com.sjcl.zrsy.domain.dto;

import org.hibernate.validator.constraints.Length;

public class LogisticsReception {
    //猪id
    @Length(min = 13,max = 13,message = "猪id长度为13位")
    private String id;

    @Length(min = 6,max = 6,message = "物流公司长度为6位")
    private String logisticsId;//物流公司ID

    @Length(min = 7,max = 7,message = "车牌号长度为6位")
    private String carId;

    @Length(min = 18,max = 18,message = "司机身份证号长度为18位")
    private String driverId;

    public LogisticsReception(String id, String logisticsId, String carId, String driverId) {
        this.id = id;
        this.logisticsId = logisticsId;
        this.carId = carId;
        this.driverId = driverId;
    }

    public LogisticsReception() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
