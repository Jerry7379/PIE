package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.AllData;
import com.sjcl.zrsy.domain.dto.Current;
import com.sjcl.zrsy.domain.dto.CurrentWeekData;
import com.sjcl.zrsy.domain.dto.Ratio;

import java.util.List;

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

    /**
     * get current week data
     * @return
     */
    CurrentWeekData getCurrentWeekData();

    /**
     * get alldata and currentweekdata
     * @return
     */
    Current getCurrentData();

    List<Ratio> getVarietyRatio(String scope);

    List<Ratio> getGenderRatio(String scope);

    List<Ratio> getOutBarRatio(String scope);
}
