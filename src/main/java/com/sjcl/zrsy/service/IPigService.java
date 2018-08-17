package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.Pig_Birth;

public interface IPigService {
    void insertBirth(Pig_Birth pigBirth);
    void insertFPO(FarmOperation farmOperation);
}
