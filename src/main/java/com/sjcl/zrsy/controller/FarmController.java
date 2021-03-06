package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.service.IFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.ResourceBundle;

@RestController
public class FarmController {

    @Autowired
    IFarmService farmService;

    //i18n
    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.getDefault());

    @PostMapping("/farmreception")
    public String farmreception(@RequestBody FarmReception farmReception) {
        if(!farmService.idCardExists(farmReception.getId())){
            if(farmService.farmReception(farmReception)){
                return resourceBundle.getString("SuccessfulOperation");
            }
            else {
                return resourceBundle.getString("OperationFailed,re-enter");
            }
        }
        else {
            //TODO i18n
            return "此小猪已经出生，id错误";
        }
    }

    @PostMapping("/farmoperation")
    public String farmoperation(@RequestBody Operation operation) {
        if(farmService.idCardExists(operation.getId())) {
            if (farmService.farmOperation(operation)) {
                return resourceBundle.getString("SuccessfulOperation");
            } else {
                return resourceBundle.getString("OperationFailed,re-enter");
            }
        }
        else{
            //TODO i18n
            return "小猪还未出生";
        }


    }



}
