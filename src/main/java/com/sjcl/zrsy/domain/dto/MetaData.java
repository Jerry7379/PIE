package com.sjcl.zrsy.domain.dto;

public class MetaData {
    private String type;
    private String role;
    private Object object;

    public MetaData(String type, String role, Object object) {
        this.type = type;
        this.role = role;
        this.object = object;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
