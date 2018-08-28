package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.SlaughterReception;
import com.sjcl.zrsy.domain.SlaughterOperation;
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
    public String  slaughterreception(@RequestBody SlaughterReception checker){
        if(slaughterreceiver.slaughterreception(checker))
            return "操作成功";
        else
            return "操作失败";
    }

    @PostMapping("/slaughteroperation")
    public String  slaughterhouseoperation(@RequestBody SlaughterOperation slaughterAcid){
        String info[]=slaughterAcid.getContent().split(";");
        if(info.length==2){
        slaughterAcid.setContent(info[0]);
        slaughterAcid.setContent(info[1]);
        }
        else {
            slaughterAcid.setContent(info[0]);
        }

        if(slaughterreceiver.slaughteroperation(slaughterAcid))
            return "操作成功";
        else
            return "操作失败";
    }
}
