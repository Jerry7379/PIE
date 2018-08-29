package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.MarketOperation;
import com.sjcl.zrsy.domain.MarketReception;
import com.sjcl.zrsy.service.implement.IMarketService;
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
        int i;
        for(i=0;i<info.length;) {
            marketReception.setId(info[i]);
            if(marketService.marketreception(marketReception)) {
                i++;
            }
            else {
                break;
            }
        }

        if(i==info.length) {
            //return "操作成功";
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            //return "操作失败,重新输入";
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }

    @PostMapping("/marketoperation")
    public String marketoperation(@RequestBody MarketOperation marketOperation) {
        if(marketService.marketoperation(marketOperation)) {
            //return "操作成功";
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            //return "操作失败,重新输入";
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }
}