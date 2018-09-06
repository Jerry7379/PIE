package com.sjcl.zrsy.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.hibernate.validator.constraints.Length;

public class SlaughterOperation {
    @JsonProperty(value = "id")
    @Length(min = 13,max = 13,message = "猪ID长度13位")
    private String id;
    private String operation;
    @JsonProperty(value="content")
    private String content;//当操作不是排酸，这项为操作的主要内容，当操作为排酸时，这个为排酸人员id，pigIsacid为是否排酸

    private Integer isAcid;

    @JsonProperty(value="remark")
    private String remark;

    @JsonProperty(value="time")
    private String time;

    public SlaughterOperation(String id, String operation, String content, Integer isAcid, String remark, String time) {
        this.id = id;
        this.operation = operation;
        this.content = content;
        this.isAcid = isAcid;
        this.remark = remark;
        this.time = time;
    }

    public SlaughterOperation() {
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

    public Integer getIsAcid() {
        return isAcid;
    }

    public void setIsAcid(Integer isAcid) {
        this.isAcid = isAcid;
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

    /**
     * 排酸信息
     * @return
     */
    public TraceabilityIdcard toAcid() {
        TraceabilityIdcard acid = new TraceabilityIdcard();
        acid.setAciderId(this.content);
        acid.setIsacid(this.isAcid);
        acid.setId(this.id);
        return acid;
    }

    /**
     * 转为通用Operation
     * @return
     */
    public Operation toOperation() {
        Operation operation = new Operation();
        operation.setId(this.id);
        operation.setOperation(this.operation);
        operation.setContent(this.content + "+" + this.isAcid);
        operation.setRemark(this.remark);
        operation.setTime(this.time);
        return operation;
    }
}
