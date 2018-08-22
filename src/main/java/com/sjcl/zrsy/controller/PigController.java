package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.LogisticsReceive;
import com.sjcl.zrsy.domain.LogisticsWork;
import com.sjcl.zrsy.domain.Pig_Birth;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PigController {
    @Autowired
    IPigService pigService;


    @PostMapping("/FPB")
    @ResponseBody
    public String FPB(@RequestBody Pig_Birth pigBirth) {

        return pigService.insertBirth(pigBirth);
    }

    @PostMapping("/FPO")
    //@RequestBody
    public String FPO(@RequestBody FarmOperation farmOperation) {

        return pigService.insertFPO(farmOperation);
    }

    @PostMapping("/LR")
    //@RequestBody
    public String LR(@RequestBody LogisticsReceive logisticsReceive) {

        return pigService.insertLR(logisticsReceive);
    }

    @PostMapping("/LW")
    //@RequestBody
    public String LW(@RequestBody LogisticsWork logisticsWork) {

        return pigService.insertLW(logisticsWork);
    }


    }
