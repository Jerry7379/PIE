package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.IPigDao;
import com.sjcl.zrsy.dao.implement.bigchaindb.PigDao;
import com.sjcl.zrsy.domain.dto.*;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 返回雌雄数量，各品种的数量
     * @return
     */
    @Override
    public VarietyRatio getVarirtyRation() {
        VarietyRatio varietyRatio=new VarietyRatio();
        varietyRatio.setBreedGlobal(pigDao.getRatio(IPigDao.RADIO_VARIETY));
        varietyRatio.setBreedSpot(pigDao.getUnspentRatio(IPigDao.RADIO_VARIETY));
        varietyRatio.setGenderGlobal(pigDao.getRatio(IPigDao.RADIO_GENDER));
        varietyRatio.setGenderSpot(pigDao.getUnspentRatio(IPigDao.RADIO_GENDER));
        return varietyRatio;
    }

    /**
     * 返回年龄分布
     * @return
     */
    @Override
    public Map getAgedistributed() {
        Map<String,Integer> map=new HashMap<>();
        map.put("num60",pigDao.getAgedistributed(IPigDao.AGE_60));
        map.put("num120",pigDao.getAgedistributed(IPigDao.AGE_120));
        map.put("num180",pigDao.getAgedistributed(IPigDao.AGE_180));
        map.put("num240",pigDao.getAgedistributed(IPigDao.AGE_240));

        return map;
    }

    public static void main(String[] args) {
        LocalDate s=LocalDate.now();
        System.out.println(s);
    }
}
