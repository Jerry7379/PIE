package com.sjcl.zrsy.domain;

public class MarketWork {
    private String pigId;
    private String pigOperation;
    private String pigContent;
    private String pigRemark;
    private String pigTime;

    public String getPigTime() {
        return pigTime;
    }

    public void setPigTime(String pigTime) {
        this.pigTime = pigTime;
    }

    public String getPigId() {
        return pigId;
    }

    public void setPigId(String pigId) {
        this.pigId = pigId;
    }

    public String getPigOperation() {
        return pigOperation;
    }

    public void setPigOperation(String pigOperation) {
        this.pigOperation = pigOperation;
    }

    public String getPigContent() {
        return pigContent;
    }

    public void setPigContent(String pigContent) {
        this.pigContent = pigContent;
    }

    public String getPigRemark() {
        return pigRemark;
    }

    public void setPigRemark(String pigRemark) {
        this.pigRemark = pigRemark;
    }
}
