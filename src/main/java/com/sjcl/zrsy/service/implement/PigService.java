package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.IPigDao;
import com.sjcl.zrsy.domain.dto.Data;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;

public class PigService implements IPigService {

    @Autowired
    IPigDao pigDao;

    @Override
    public int getUnspentCountCurrentRegistration() {
        return pigDao.getUnspentCountCurrentRegistration();
    }

    @Override
    public Data getAllData() {
        Data data = new Data();
        data.setTotalCount(getUnspentCountCurrentRegistration());
        data.setOutBarCount(pigDao.getSpentCountCurrentRegistration());
//        data.setOutBarAvgWeight();
        return data;
    }
}
