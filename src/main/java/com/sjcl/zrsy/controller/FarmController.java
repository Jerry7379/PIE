package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.FarmReception;
import com.sjcl.zrsy.service.implement.IFarmService;
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
    //国际化
    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.US);
    @PostMapping("/farmreception")
    public String farmreception(@RequestBody FarmReception pigBirth) {
        if(farmService.farmReception(pigBirth)){
            //return "操作成功";
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            //return "操作失败,重新输入";
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }

    @PostMapping("/farmoperation")
    public String farmoperation(@RequestBody FarmOperation farmOperation) {
        if (farmService.farmOperation(farmOperation)) {
            //return "操作成功";
            return resourceBundle.getString("SuccessfulOperation");
        } else {
            //return "操作失败,重新输入";
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }
}
