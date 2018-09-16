package com.sjcl.zrsy.tendermint;

@ActionClass
public class Transaction {
    private String action;
    private String param;

    public Transaction() {}

    public Transaction(String action, String param) {
        this.action = action;
        this.param = param;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
