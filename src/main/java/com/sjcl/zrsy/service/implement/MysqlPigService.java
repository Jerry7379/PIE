package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.PigDao;
import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.Pig_Birth;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MysqlPigService implements IPigService {
    @Autowired
    PigDao pigDao;
    @Override
    public void insertBirth(Pig_Birth pigBirth)
    {
        pigDao.insertBirth(pigBirth);
    }

    @Override
    public void insertFPO(FarmOperation farmOperation)
    {
        pigDao.insertFPO(farmOperation);
    }


}
