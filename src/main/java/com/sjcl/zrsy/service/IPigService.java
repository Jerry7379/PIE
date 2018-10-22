package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.AllData;
import com.sjcl.zrsy.domain.dto.CurrentWeekData;

/**
 * pig service
 */
public interface IPigService {
    /**
     * get current registration pig unspent count
     * @return
     */
    int getUnspentCountCurrentRegistration();


    /**
     * get all data
     * @return
     */
    AllData getAllData();

    CurrentWeekData getCurrentWeekData();
}
