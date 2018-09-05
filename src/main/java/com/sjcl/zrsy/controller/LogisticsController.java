package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.LogisticsReception;
import com.sjcl.zrsy.domain.po.LogisticsOperation;
import com.sjcl.zrsy.service.ILogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.ResourceBundle;

@RestController
public class LogisticsController {
    @Autowired
    ILogisticsService logisticsService;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.US);

    @PostMapping("/logisticsoperation")
    public String  logisticsoperation(@RequestBody LogisticsOperation logisticsOperation){
        String info[]=logisticsOperation.getId().split(";");

        for(int i = 0; i < info.length; i++)
        {
            logisticsOperation.setId(info[i]);
            if(!logisticsService.logisticsoperation(logisticsOperation)) {
                //return "操作失败,重新输入";
                return resourceBundle.getString("OperationFailed,re-enter");
            }
        }

        //return "操作成功";
        return resourceBundle.getString("SuccessfulOperation");
    }

    @PostMapping("/logisticsreception")
    public String  logisticsreception(@RequestBody LogisticsReception logisticsReception){
        if( logisticsService.logisticsreception(logisticsReception)) {
            //return "操作成功";
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            //return "操作失败,重新输入";
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }
}
