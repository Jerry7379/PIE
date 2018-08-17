package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.Pig_Birth;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PigController {
    @Autowired
    IPigService pigService;

    @PostMapping("/FPB")
    public void FPB(@RequestBody Pig_Birth pigBirth) {

        pigService.insertBirth(pigBirth);
    }

    @PostMapping("/FPO")
    public void FPO(@RequestBody FarmOperation farmOperation) {

        pigService.insertFPO(farmOperation);
    }
}
