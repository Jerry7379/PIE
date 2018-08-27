package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.FarmReception;
import com.sjcl.zrsy.service.implement.IFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarmController {
    @Autowired
    IFarmService farmService;

    @PostMapping("/farmreception")
    public String farmreception(@RequestBody FarmReception pigBirth) {
        if(farmService.farmReception(pigBirth)){
            return "操作成功";
        }
        else {
            return "操作失败，重新输入";
        }
    }

    @PostMapping("/farmoperation")
    public String farmoperation(@RequestBody FarmOperation farmOperation) {
        if (farmService.farmOperation(farmOperation))
            return "操作成功";
        else
            return "操作失败，重新输入";
    }
}
