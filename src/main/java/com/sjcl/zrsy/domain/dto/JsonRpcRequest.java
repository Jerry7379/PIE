package com.sjcl.zrsy.domain.dto;

public class JsonRpcRequest {
    private String jsonrpc = "2.0";
    private String id;
    private String method;
    private Object params;

    public JsonRpcRequest(String id, String method) {
        this.id = id;
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }
}
