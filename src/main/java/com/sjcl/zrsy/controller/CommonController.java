package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.domain.dto.RestfulResult;
import com.sjcl.zrsy.domain.dto.TransferOperation;
import com.sjcl.zrsy.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CommonController {
    @Autowired
    ITransferService transferService;

    @PostMapping("/transfer")
    public RestfulResult farmoperation(@RequestBody TransferOperation operation) {
        if (!transferService.isNext(operation.getRegistrationId())) {
            return RestfulResult.errorMsg("您输入的不是" + KeyPairHolder.getUser().getNextType());
        }

        try {
            transferService.transfer(operation.getPigId(), operation.getRegistrationId());
            return RestfulResult.ok();
        } catch (Exception e) {
            return RestfulResult.errorMsg(e.getMessage());
        }
    }
}
