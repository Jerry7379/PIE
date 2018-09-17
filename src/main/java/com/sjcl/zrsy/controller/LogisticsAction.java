package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.LogisticsOperation;
import com.sjcl.zrsy.domain.dto.LogisticsReception;
import com.sjcl.zrsy.service.ILogisticsService;
import com.sjcl.zrsy.tendermint.ActionClass;
import com.sjcl.zrsy.tendermint.ActionMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.ResourceBundle;

@ActionClass
public class LogisticsAction {

    @Autowired
    ILogisticsService logisticsService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.US);

    @ActionMethod("logisticsoperation")
    public String  logisticsoperation(LogisticsOperation logisticsOperation){
        String info[]=logisticsOperation.getId().split(";");

        for(int i = 0; i < info.length; i++)
        {
            logisticsOperation.setId(info[i]);
            if(!logisticsService.logisticsoperation(logisticsOperation)) {
                return resourceBundle.getString("OperationFailed,re-enter");
            }
        }

        return resourceBundle.getString("SuccessfulOperation");
    }

    @ActionMethod("logisticsreception")
    public String  logisticsreception(LogisticsReception logisticsReception){
        if( logisticsService.logisticsreception(logisticsReception)) {
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }
}
