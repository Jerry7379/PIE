package com.sjcl.zrsy.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferOperation {

    private String pigId;
    @JsonProperty(value="roleId")
    private String roleId;

    private String type;

    public TransferOperation() {}

    public String getPigId() {
        return pigId;
    }

    public void setPigId(String pigId) {
        this.pigId = pigId;
    }

    public String getRoleid() {
        return roleId;
    }

    public void setRoleid(String roleid) {
        this.roleId = roleid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
