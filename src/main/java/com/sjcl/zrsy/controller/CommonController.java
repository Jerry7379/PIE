package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.dto.RestfulResult;
import com.sjcl.zrsy.domain.dto.TransferOperation;
import com.sjcl.zrsy.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @Autowired
    ITransferService transferService;

    @PostMapping("/transfer")
    public RestfulResult farmoperation(@RequestBody TransferOperation operation) {
        try {
            transferService.transfer(operation);
            return RestfulResult.ok("OK");
        } catch (Exception e) {
            return RestfulResult.errorMsg(e.getMessage());
        }
    }
}
