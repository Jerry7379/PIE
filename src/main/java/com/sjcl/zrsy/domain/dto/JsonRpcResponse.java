package com.sjcl.zrsy.domain.dto;

public class JsonRpcResponse {
    private String jsonrpc;
    private String id;
    private Object result;

    public JsonRpcResponse() {
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}