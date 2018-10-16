package com.sjcl.zrsy.domain.dto;

import javax.validation.constraints.NotNull;

public class TransferOperation {
    @NotNull
    private String pigId;
    @NotNull
    private String registrationId;

    public TransferOperation() {}

    public void setPigId(String pigId) {
        this.pigId = pigId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getPigId() {
        return pigId;
    }

    public String getRegistrationId() {
        return registrationId;
    }
}
