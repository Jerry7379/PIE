package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.IPigDao;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;

public class PigService implements IPigService {

    @Autowired
    IPigDao pigDao;

    @Override
    public int getCountCurrentRegistration() {
        return pigDao.getCountCurrentRegistration();
    }
}
