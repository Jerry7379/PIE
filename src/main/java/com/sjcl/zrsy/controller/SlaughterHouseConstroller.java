package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.SlaughterOperation;
import com.sjcl.zrsy.domain.dto.SlaughterReception;
import com.sjcl.zrsy.service.ISlaughterHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.ResourceBundle;

@RestController
public class SlaughterHouseConstroller {

    @Autowired
    ISlaughterHouseService slaughterreceiver;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.US);

    @PostMapping("/slaughterreception")
    public String  slaughterreception(@RequestBody SlaughterReception checker){
        if(slaughterreceiver.slaughterreception(checker)) {
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }

    @PostMapping("/slaughteroperation")
    public String  slaughterhouseoperation(@RequestBody SlaughterOperation slaughterAcid){
        String info[]=slaughterAcid.getContent().split(";");
        if(info.length==2){
            slaughterAcid.setContent(info[0]);
            slaughterAcid.setIsAcid(Integer.parseInt(info[1]));
        }
        else {
            slaughterAcid.setContent(info[0]);
        }

        if(slaughterreceiver.slaughteroperation(slaughterAcid)) {
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }
}
