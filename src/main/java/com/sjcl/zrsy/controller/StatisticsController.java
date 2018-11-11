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

    /**
     * get alldata and currentWeekDate
     * @return
     */
    @GetMapping("/current")
    public RestfulResult CurrentData() {
        return RestfulResult.ok(pigService.getCurrentData());
    }

    /**
     *
     * @return
     */
    @GetMapping("/varietyRatio")
    public RestfulResult varietyRatio() {
        return RestfulResult.ok(pigService.getVarirtyRation());
    }

    @GetMapping("/days")
    public RestfulResult days(){
        return RestfulResult.ok(pigService.getAgedistributed());
    }

    /**
     * 获得年龄与出栏体重的分布
     * @return
     */
    @GetMapping("/dayage")
    public RestfulResult dayage(){
        return RestfulResult.ok(pigService.getAgeandWeight());
    }

}
