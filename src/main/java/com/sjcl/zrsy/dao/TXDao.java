package com.sjcl.zrsy.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import com.sjcl.zrsy.domain.dto.JsonRpcRequest;
import com.sjcl.zrsy.domain.dto.JsonRpcRespones;
import com.sjcl.zrsy.domain.dto.JsonRpcResponesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Component
public class TXDao {
    @Autowired
    RestTemplate restTemplate;

    private String url;

    public TXDao(@Value("#{chain.url}") String url) {
        this.url = url;
    }

    public String sendTX(Object object) throws JSONException {
        JsonRpcRequest jsonRpcRequest = buildBroadcatTxRequest(object);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, JSON.toJSONString(jsonRpcRequest),JSONObject.class);
        JsonRpcRespones jsonRpcRespones =JSON.parseObject(responseEntity.getBody().toString(),JsonRpcRespones.class);
        JsonRpcResponesResult jsonRpcResponesResult=JSON.parseObject(jsonRpcRespones.getResult().toString(),JsonRpcResponesResult.class);
        return jsonRpcResponesResult.getHash();
    }


    private JsonRpcRequest buildBroadcatTxRequest(Object object) {
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest(String.valueOf(object.hashCode()), "broadcast_tx_commit");
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] strBytes = JSON.toJSONString(object).getBytes();
        String encodedStr=encoder.encodeToString(strBytes);
        Map m=new HashMap();
        m.put("tx",encodedStr);
        jsonRpcRequest.setParams(m);
        return jsonRpcRequest;
    }



}
