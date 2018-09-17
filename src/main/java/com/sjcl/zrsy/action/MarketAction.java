package com.sjcl.zrsy.action;

import com.sjcl.zrsy.domain.dto.MarketReception;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.service.IMarketService;
import com.sjcl.zrsy.tendermint.ActionClass;
import com.sjcl.zrsy.tendermint.ActionMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.ResourceBundle;

@ActionClass
public class MarketAction {

    @Autowired
    IMarketService marketService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.US);

    @ActionMethod("marketreception")
    public String marketreception(MarketReception marketReception) {
        String info[]=marketReception.getId().split(";");
        for(int i = 0; i < info.length; i++) {
            marketReception.setId(info[i]);
            if(!marketService.marketreception(marketReception)) {
                return resourceBundle.getString("OperationFailed,re-enter");
            }
        }

        return resourceBundle.getString("SuccessfulOperation");
    }

    @ActionMethod("marketoperation")
    public String marketoperation(Operation operation) {
        if(marketService.marketoperation(operation)) {
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }
}