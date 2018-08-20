package com.sjcl.zrsy.domain;

import java.util.Date;

public class FarmOperation {
    private String id;
    private String operation;
    private String content;
    private String remark;
    private String time;

    public FarmOperation() {}

    public FarmOperation(String id, String operation, String content, String remark, String time)
    {
        this.id=id;
        this.operation=operation;
        this.content=content;
        this.remark=remark;
        this.time=time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
