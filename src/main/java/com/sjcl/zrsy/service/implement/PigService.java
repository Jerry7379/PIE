package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.IPigDao;
import com.sjcl.zrsy.domain.dto.AllData;
import com.sjcl.zrsy.domain.dto.CurrentWeekData;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@Service
public class PigService implements IPigService {

    @Autowired
    IPigDao pigDao;

    @Override
    public int getUnspentCountCurrentRegistration() {
        return pigDao.getUnspentCountCurrentRegistration();
    }

    @Override
    public AllData getAllData() {
        AllData allData = new AllData();
        allData.setTotalCount(getUnspentCountCurrentRegistration());
        allData.setOutBarCount(pigDao.getSpentCountCurrentRegistration());
        allData.setOutBarAvgWeight(pigDao.getSpentAvgWeightCurrentRegistration());
        return allData;
    }

    @Override
    public CurrentWeekData getCurrentWeekData() {
        LocalDate start = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1).minusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);

        CurrentWeekData currentWeekData = new CurrentWeekData();
        currentWeekData.setBrith(pigDao.getBirthCountCurrentRegistration(start, end));
        currentWeekData.setOutBar(pigDao.getSpentCountCurrentRegistration(start, end));
        currentWeekData.setOutBarAvgWeight(pigDao.getSpentAvgWeightCurrentRegistration(start, end));
        return currentWeekData;
    }
}
