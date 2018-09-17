package com.sjcl.zrsy.tendermint;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(action, that.action) &&
                Objects.equals(param, that.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, param);
    }
}
