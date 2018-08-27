package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.MarketREC;
import com.sjcl.zrsy.domain.MarketWork;
import com.sjcl.zrsy.service.implement.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketController {
    @Autowired
    IMarketService marketService;

    @PostMapping("/marketreception")
    public void marketreception(@RequestBody MarketREC marketRec) {
        marketService.marketreception(marketRec);
    }

    @PostMapping("/markertoperation")
    public String markertoperation(@RequestBody MarketWork marketWork) {
        return marketService.marketoperation(marketWork);
    }
}