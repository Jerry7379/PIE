package com.sjcl.zrsy.dao;

/**
 * pig dao
 */
public interface IPigDao {
    /**
     * get current registration unspent pig count
     * @return
     */
    int getUnspentCountCurrentRegistration();

    /**
     * get current registration spent pig count
     * @return
     */
    int getSpentCountCurrentRegistration();

    double getSpentAvgWeightCurrentRegistration();
}
