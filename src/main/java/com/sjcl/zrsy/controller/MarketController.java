package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.MarketOperation;
import com.sjcl.zrsy.domain.dto.MarketReception;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.service.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.ResourceBundle;

@RestController
public class MarketController {

    @Autowired
    IMarketService marketService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.US);

    @PostMapping("/marketreception")
    public String marketreception(@RequestBody MarketReception marketReception) {
        String info[]=marketReception.getId().split(";");
        for(int i = 0; i < info.length; i++) {
            marketReception.setId(info[i]);
            if(!marketService.marketreception(marketReception)) {
                return resourceBundle.getString("OperationFailed,re-enter");
            }
        }

        return resourceBundle.getString("SuccessfulOperation");
    }

    @PostMapping("/marketoperation")
    public String marketoperation(@RequestBody Operation operation) {
        if(marketService.marketoperation(operation)) {
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }
}