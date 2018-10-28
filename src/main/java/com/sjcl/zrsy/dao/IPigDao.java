package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.Ratio;

import java.time.LocalDate;
import java.util.List;

/**
 * pig dao
 */
public interface IPigDao {
    public static final String RADIO_VARIETY = "variety";
    public static final String RADIO_GENDER = "gender";
    public static final String RADIO_OUTBAR = "outBar";
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

    List<Ratio> getRatio(String category, String scope);
}
