package com.sjcl.zrsy.controller;

import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.model.Output;
import com.bigchaindb.model.Outputs;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import com.sjcl.zrsy.dao.implement.bigchaindb.PigDao;
import com.sjcl.zrsy.dao.implement.bigchaindb.TraceabillityIdcardDao;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.security.KeyPair;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class FarmControllerTest {
    public static void main(String[] args) throws IOException {

        BigchainDbConfigBuilder
                .baseUrl("http://127.0.0.1:9984")
                .setup();

        //OutputsApi.getUnspentOutputs("3QNMXqsmZrT7q7brGcLGFxswYbT5QoXMaNks1nWZtGMa").getOutput().get(0);
        System.out.printf(OutputsApi.getUnspentOutputs("3QNMXqsmZrT7q7brGcLGFxswYbT5QoXMaNks1nWZtGMa").getOutput().get(0).getPublicKeys().get(0));
    }
}

