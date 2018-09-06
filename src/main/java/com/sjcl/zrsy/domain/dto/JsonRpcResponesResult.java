package com.sjcl.zrsy.domain.dto;

public class JsonRpcResponesResult {
    private Object check_tx;
    private Object deliver_tx;
    private String hash;
    private String height;
    public JsonRpcResponesResult(){}

    public Object getCheck_tx() {
        return check_tx;
    }

    public void setCheck_tx(Object check_tx) {
        this.check_tx = check_tx;
    }

    public Object getDeliver_tx() {
        return deliver_tx;
    }

    public void setDeliver_tx(Object deliver_tx) {
        this.deliver_tx = deliver_tx;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
