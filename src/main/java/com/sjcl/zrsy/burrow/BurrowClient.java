package com.sjcl.zrsy.burrow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.math.BigInteger;

public class BurrowClient {

    private static final String CALL_PARAM_FROM = "0000000000000000000000000000000000000000";


    private HttpService httpService;

    public BurrowClient(HttpService httpService) {
        this.httpService = httpService;
    }

    public BurrowClient() {
        this(new HttpService());
    }

    public String call(String address, String data) throws IOException {
        JSONObject request = new JSONObject();
        request.put("jsonrpc", "2.0");
        request.put("id", "0");
        request.put("method", "burrow.call");

        JSONObject params = new JSONObject();
        params.put("from", CALL_PARAM_FROM);
        params.put("address", address);
        params.put("data", data);
        request.put("params", params);

        InputStream result = httpService.performIO(request.toJSONString());
        if (result != null) {
            String response = getResponse(result);
            JSONObject resp = JSON.parseObject(response);
            return resp.getJSONObject("result").getString("Return");
        } else {
            return null;
        }
    }

    private String getResponse(InputStream result) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(result, "utf-8"));
        StringWriter sw = new StringWriter();

        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) != -1) {
            sw.write(buf, 0, len);
        }
        reader.close();
        sw.close();
        return sw.toString();
    }

    public String transactAndHold(String address, String data, String privateKey, BigInteger gasLimit, BigInteger fee) throws IOException {
        JSONObject request = new JSONObject();
        request.put("jsonrpc", "2.0");
        request.put("id", "0");
        request.put("method", "burrow.call");

        JSONObject params = new JSONObject();
        JSONObject inputAccount = new JSONObject();
        inputAccount.put("privateKey", privateKey);
        params.put("inputAccount", inputAccount);
        params.put("address", address);
        params.put("data", data);
        params.put("gasLimit", gasLimit);
        params.put("fee", fee);



        //发送请求，返回response的body
        InputStream result = httpService.performIO(request.toJSONString());

        if (result != null) {
            String response = getResponse(result);
            JSONObject resp = JSON.parseObject(response);
            JSONObject ret = resp.getJSONObject("result");
            String exception = ret.getString("Exception");
            if (exception != null) {
                throw new RuntimeException(exception);
            }
            return ret.getString("Return");
        } else {
            return null;
        }
    }
}
