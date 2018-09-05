package com.sjcl.zrsy.domain.po;


import org.hibernate.validator.constraints.Length;

public class FarmOperation {

    //猪id
    @Length(min = 13,max = 13,message = "猪ID长度为13")
    private String id;

    //操作类型
    private String operation;

    //操作主要内容
    @Length(max = 50,min = 10,message = "主要内容长度10-50")
    private String content;

    //备注
    @Length(max = 50,min = 10,message = "备注长度10-50")
    private String remark;

    //时间
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
