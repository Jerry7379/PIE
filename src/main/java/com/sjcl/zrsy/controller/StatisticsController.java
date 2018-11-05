package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.RestfulResult;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pigs")
public class StatisticsController {

    @Autowired
    IPigService pigService;

    @GetMapping("/counts")
    public RestfulResult counts() {
        return RestfulResult.ok(pigService.getUnspentCountCurrentRegistration());
    }

    /**
     * get alldata and currentWeekDate
     * @return
     */
    @GetMapping("/current")
    public RestfulResult CurrentData() {
        return RestfulResult.ok(pigService.getCurrentData());
    }


    @GetMapping("/varietyRatio")
    public RestfulResult varietyRatio(@RequestParam(name = "scope") String scope) {
        return RestfulResult.ok(pigService.getVarietyRatio(scope));
    }

    @GetMapping("/genderRatio")
    public RestfulResult genderRatio(@RequestParam(name = "scope") String scope) {
        return RestfulResult.ok(pigService.getGenderRatio(scope));
    }

    @GetMapping("/outBarRatio")
    public RestfulResult outBarRatio(@RequestParam(name = "scope") String scope) {
        return RestfulResult.ok(pigService.getOutBarRatio(scope));
    }
}
