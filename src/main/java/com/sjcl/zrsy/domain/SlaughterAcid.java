package com.sjcl.zrsy.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

public class SlaughterAcid {
    private String pigId;
    private String pigOperation;
    private String pigPspid;
    private String pigIsacid;
    private String pigRemark;
    private String pigTime;

    boolean s;
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

    public String getPigPspid() {
        return pigPspid;
    }

    public void setPigPspid(String pigPspid) {
        this.pigPspid = pigPspid;
    }

    public String getPigIsacid() {
        return pigIsacid;
    }

    public void setPigIsacid(String pigIsacid) {
        this.pigIsacid = pigIsacid;
    }

    public String getPigRemark() {
        return pigRemark;
    }

    public void setPigRemark(String pigRemark) {
        this.pigRemark = pigRemark;
    }

    public String getPigTime() {
        return pigTime;
    }

    public void setPigTime(String pigTime) {
        this.pigTime = pigTime;
    }
}
