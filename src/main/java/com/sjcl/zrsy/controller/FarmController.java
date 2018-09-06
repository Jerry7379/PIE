package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.FarmOperation;
import com.sjcl.zrsy.domain.dto.FarmReception;
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
    //国际化
    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.getDefault());
    @PostMapping("/farmreception")
    public String farmreception(@RequestBody FarmReception farmReception) {
        if(farmService.idCardExists(farmReception.getId())){
            if(farmService.farmReception(farmReception)){
                //return "操作成功";
                return resourceBundle.getString("SuccessfulOperation");
            }
            else {
                //return "操作失败,重新输入";
                return resourceBundle.getString("OperationFailed,re-enter");
            }
        }
        else {
            return "此小猪已经出生，id错误";//未加国际化
        }
    }

    @PostMapping("/farmoperation")
    public String farmoperation(@RequestBody FarmOperation farmOperation) {
        if(farmService.idCardExists(farmOperation.getId())) {
            if (farmService.farmOperation(farmOperation)) {
                //return "操作成功";
                return resourceBundle.getString("SuccessfulOperation");
            } else {
                //return "操作失败,重新输入";
                return resourceBundle.getString("OperationFailed,re-enter");
            }
        }
        else{
            return "小猪还未出生";
        }


    }



}
