package com.sjcl.zrsy.dao;

import java.time.LocalDate;

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

    int getBirthCountCurrentRegistration(LocalDate start, LocalDate end);

    double getSpentAvgWeightCurrentRegistration(LocalDate start, LocalDate end);

    int getSpentCountCurrentRegistration(LocalDate start, LocalDate end);
}
