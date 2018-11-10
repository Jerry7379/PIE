package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.IPigDao;
import com.sjcl.zrsy.dao.implement.bigchaindb.PigDao;
import com.sjcl.zrsy.domain.dto.AllData;
import com.sjcl.zrsy.domain.dto.Current;
import com.sjcl.zrsy.domain.dto.CurrentWeekData;
import com.sjcl.zrsy.domain.dto.Ratio;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
public class PigService implements IPigService {

    @Autowired
    IPigDao pigDao;

    @Override
    public int getUnspentCountCurrentRegistration() {
        return pigDao.getUnspentCountCurrentRegistration();
    }

    /**
     * 获得场中所有数据
     * @return
     */
    @Override
    public AllData getAllData() {
        AllData allData = new AllData();
        allData.setTotalCount(getUnspentCountCurrentRegistration());
        allData.setOutBarCount(pigDao.getSpentCountCurrentRegistration());
        allData.setOutBarAvgWeight(pigDao.getSpentAvgWeightCurrentRegistration());
        return allData;
    }

    /**
     * 获得当前一周数据
     * @return
     */
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

    @Override
    public Current getCurrentData(){
        Current current=new Current();
        current.setAllData(getAllData());
        current.setCurrentWeekData(getCurrentWeekData());
        return current;
    }
    @Override
    public List<Ratio> getVarietyRatio(String scope) {
        return pigDao.getRatio(PigDao.RADIO_VARIETY, scope);
    }

    @Override
    public List<Ratio> getGenderRatio(String scope) {
        return pigDao.getRatio(PigDao.RADIO_GENDER, scope);
    }

    @Override
    public List<Ratio> getOutBarRatio(String scope) {
        return pigDao.getRatio(PigDao.RADIO_OUTBAR, scope);
    }
}
