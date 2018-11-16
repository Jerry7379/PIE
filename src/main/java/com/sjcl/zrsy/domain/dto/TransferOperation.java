package com.sjcl.zrsy.domain.dto;

import javax.validation.constraints.NotNull;

public class TransferOperation {
    @NotNull
    private String pigId;
    @NotNull
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
