package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.SlaughterOperation;
import com.sjcl.zrsy.domain.dto.SlaughterReception;
import com.sjcl.zrsy.service.ISlaughterHouseService;
import com.sjcl.zrsy.tendermint.ActionClass;
import com.sjcl.zrsy.tendermint.ActionMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.ResourceBundle;

@ActionClass
public class SlaughterHouseAction {

    @Autowired
    ISlaughterHouseService slaughterreceiver;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.US);

    @ActionMethod("slaughterreception")
    public String  slaughterreception(SlaughterReception checker){
        if(slaughterreceiver.slaughterreception(checker)) {
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }

    @ActionMethod("slaughteroperation")
    public String  slaughterhouseoperation(SlaughterOperation slaughterAcid){
        String info[]=slaughterAcid.getContent().split(";");
        if(info.length==2){
            slaughterAcid.setContent(info[0]);
            slaughterAcid.setIsAcid(Integer.parseInt(info[1]));
        }
        else {
            slaughterAcid.setContent(info[0]);
        }

        if(slaughterreceiver.slaughteroperation(slaughterAcid)) {
            return resourceBundle.getString("SuccessfulOperation");
        }
        else {
            return resourceBundle.getString("OperationFailed,re-enter");
        }
    }
}
