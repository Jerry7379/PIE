package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.Ratio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * pig dao
 */
public interface IPigDao {
    public static final String RADIO_VARIETY = "variety";
    public static final String RADIO_GENDER = "gender";

    public static final int AGE_60=60;
    public static final int AGE_120=120;
    public static final int AGE_180=180;
    public static final int AGE_240=240;


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

    /**
     * 全局查询category的数量
     * @param category
     * @return
     */
    List<Ratio> getRatio(String category);

    /**
     * 场中查找category的数量
     * @param category
     * @return
     */
    List<Ratio> getUnspentRatio(String category);

    /**
     * 返回某一个年龄段的个数
     * @param day
     * @return
     */
    int getAgedistributed(int day);
}
