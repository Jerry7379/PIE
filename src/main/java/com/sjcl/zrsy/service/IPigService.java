package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.*;

import java.util.List;
import java.util.Map;

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

    /**
     * 返回养殖场的场中数据和全局的数据
     * @return
     */
    VarietyRatio getVarirtyRation();

    /**
     * 返回年龄分布
     * @return
     */
    Map getAgedistributed();

}
