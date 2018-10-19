package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.Data;

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
    Data getAllData();
}
