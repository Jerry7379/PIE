package com.sjcl.zrsy.domain;

public class piglife {
    private String id ;
    private String operation;
    private String content;
    private String remark;
    private String time; // 0000-00-00 00:00:00

    public piglife()
    {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
