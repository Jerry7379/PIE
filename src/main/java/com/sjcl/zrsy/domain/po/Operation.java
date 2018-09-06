package com.sjcl.zrsy.domain.po;

import org.hibernate.validator.constraints.Length;

public class Operation {
    @Length(min = 13,max = 13,message = "猪ID长度为13")
    private String id;

    private String operation;

    @Length(max = 50,min = 10,message = "主要内容长度10-50")
    private String content;

    @Length(max = 50,min = 10,message = "备注长度10-50")
    private String remark;

    private String time;

    public Operation() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
