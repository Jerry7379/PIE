package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.LogisticsReceive;
import com.sjcl.zrsy.domain.LogisticsWork;
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
    public String  slaughterhouseoperation(@RequestBody LogisticsWork logisticsWork){
        return logisticsService.logisticsoperation(logisticsWork);
    }

    @PostMapping("/logisticsreception")
    public String  slaughterreception(@RequestBody LogisticsReceive logisticsReceive){
        return logisticsService.logisticsreception(logisticsReceive);
    }
}
