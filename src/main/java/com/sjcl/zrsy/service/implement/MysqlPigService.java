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
    public String insertBirth(Pig_Birth pigBirth)
    {
       return pigDao.insertBirth(pigBirth);
    }

    @Override
    public String insertFPO(FarmOperation farmOperation)
    {
       return pigDao.insertFPO(farmOperation);
    }


}
