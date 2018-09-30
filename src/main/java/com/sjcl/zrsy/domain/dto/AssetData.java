package com.sjcl.zrsy.domain.dto;

import java.util.Optional;

public class AssetData<T> {
    private T data;
    private String type;

    public AssetData(T data) {
        this.data = data;
        this.type = data.getClass().getCanonicalName();
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

    public Optional optional() {
        return Optional.ofNullable(data);
    }
}
