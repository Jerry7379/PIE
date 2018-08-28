package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.LogisticsReceive;
import com.sjcl.zrsy.domain.LogisticsOperation;
import com.sjcl.zrsy.service.implement.ILogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsController {
    @Autowired
    ILogisticsService logisticsService;

    @PostMapping("/logisticsoperation")
    public String  slaughterhouseoperation(@RequestBody LogisticsOperation logisticsOperation){
        String info[]=logisticsOperation.getId().split(";");
        int i;
        for(i=0;i<info.length;)
        {
            logisticsOperation.setId(info[i]);
            if(logisticsService.logisticsoperation(logisticsOperation))
                i++;
            else
                break;
        }
        if(i==info.length)
            return "操作成功";
        else
            return "操作失败";
    }

    @PostMapping("/logisticsreception")
    public String  slaughterreception(@RequestBody LogisticsReceive logisticsReceive){
        if( logisticsService.logisticsreception(logisticsReceive))
            return "操作成功";
        else
            return "操作失败";
    }
}
