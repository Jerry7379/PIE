package com.sjcl.zrsy.domain.dto;

import javax.validation.constraints.NotNull;

public class TransferOperation {
    @NotNull
    private String pigId;
    @NotNull
    private String publicKeyInHex;

    public TransferOperation() {}

    public void setPigId(String pigId) {
        this.pigId = pigId;
    }

    public String getPigId() {
        return pigId;
    }

    public String getPublicKeyInHex() {
        return publicKeyInHex;
    }

    public void setPublicKeyInHex(String publicKeyInHex) {
        this.publicKeyInHex = publicKeyInHex;
    }
}
