package com.sjcl.zrsy.domain;

public class LogisticsReceive {
    private String id;
    private String logisticsid;//物流公司ID
    private String carid;
    private String driverid;

    public LogisticsReceive(String id, String logisticsid, String carid, String driverid) {
        this.id = id;
        this.logisticsid = logisticsid;
        this.carid = carid;
        this.driverid = driverid;
    }

    public LogisticsReceive() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogisticsid() {
        return logisticsid;
    }

    public void setLogisticsid(String logisticsid) {
        this.logisticsid = logisticsid;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }
}
