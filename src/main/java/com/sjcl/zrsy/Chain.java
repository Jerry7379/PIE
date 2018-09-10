package com.sjcl.zrsy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sjcl.zrsy.domain.dto.Block;
import com.sjcl.zrsy.domain.dto.JsonRpcRequest;
import com.sjcl.zrsy.domain.dto.JsonRpcResponse;
import com.sjcl.zrsy.service.data.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Component
public class Chain implements Iterable<Record> {
    private final String url;

    @Autowired
    RestTemplate restTemplate;

    public Chain(String url) {
        this.url = url;
    }

    //返回交易哈希
    public String sendTX(Object object) throws JSONException {
        JsonRpcRequest jsonRpcRequest = buildBroadcatTxRequest(object);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, JSON.toJSONString(jsonRpcRequest), JSONObject.class);
        JsonRpcResponse jsonRpcResponse = JSON.parseObject(responseEntity.getBody().toString(), JsonRpcResponse.class);
        Block jsonRpcResponesResult = JSON.parseObject(jsonRpcResponse.getResult().toString(), Block.class);
        return jsonRpcResponesResult.getHash();
    }

    private JsonRpcRequest buildBroadcatTxRequest(Object object) {
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest(String.valueOf(object.hashCode()), "broadcast_tx_commit");
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] strBytes = JSON.toJSONString(object).getBytes();
        String encodedStr = encoder.encodeToString(strBytes);
        Map m = new HashMap();
        m.put("tx", encodedStr);
        jsonRpcRequest.setParams(m);
        return jsonRpcRequest;
    }

    @Override
    public Iterator<Record> iterator() {

        return new RecordIterator();
    }

    private class RecordIterator implements Iterator<Record> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Record next() {
            return null;
        }
    }
}
