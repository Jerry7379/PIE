package com.sjcl.zrsy.domain.dto;

public class AssetData<T> {
    T data;

    public AssetData(T data) {
        this.data = data;
    }

    public String getType() {
        return data == null ? null : data.getClass().getCanonicalName();
    }


    public T getData() {
        return data;
    }

    public void setData(T object) {
        this.data = object;
    }
}
