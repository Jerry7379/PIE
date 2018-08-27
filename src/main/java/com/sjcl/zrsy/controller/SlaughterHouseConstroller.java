package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.SlaughterAcid;
import com.sjcl.zrsy.service.implement.ISlaughterHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlaughterHouseConstroller {
    @Autowired
    ISlaughterHouseService slaughterreceiver;

    @PostMapping("/slaughterreception")//屠宰检查
    public String  slaughterreception(@RequestBody PigSlaughterReceiver checker){
        return slaughterreceiver.slaughterreception(checker);
    }

    @PostMapping("/slaughterhouseoperation")
    public String  slaughterhouseoperation(@RequestBody SlaughterAcid slaughterAcid){
        return slaughterreceiver.slaughteroperation(slaughterAcid);
    }
}
