package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.LogisticsReceive;
import com.sjcl.zrsy.domain.LogisticsWork;
import com.sjcl.zrsy.domain.Pig_Birth;

public interface IPigService {
    String insertBirth(Pig_Birth pigBirth);
    String insertFPO(FarmOperation farmOperation);
    String insertLR(LogisticsReceive logisticsReceive);
    String insertLW(LogisticsWork logisticsWork);

}
