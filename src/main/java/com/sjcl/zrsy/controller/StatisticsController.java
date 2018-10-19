package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.RestfulResult;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pigs")
public class StatisticsController {

    @Autowired
    IPigService pigService;

    @GetMapping("/counts")
    public RestfulResult counts() {
        return RestfulResult.ok(pigService.getUnspentCountCurrentRegistration());
    }


    @GetMapping("/allData")
    public RestfulResult allData() {
        return RestfulResult.ok(pigService.getAllData());
    }

//    @GetMapping("/currentWeek")
//    public RestfulResult currentWeek() {
//
//    }

}
