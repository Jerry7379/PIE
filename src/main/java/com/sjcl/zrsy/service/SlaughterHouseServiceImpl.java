package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.SlaughterHouseDao;
import com.sjcl.zrsy.domain.SlaughterReception;
import com.sjcl.zrsy.domain.SlaughterOperation;
import com.sjcl.zrsy.service.implement.ISlaughterHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaughterHouseServiceImpl implements ISlaughterHouseService {
    @Autowired
    SlaughterHouseDao slaughterHouseDao;
    @Override
    public boolean slaughterreception(SlaughterReception receiver) {
        return slaughterHouseDao.updateSlaughterreception(receiver);
    }

    @Override
    public boolean slaughteroperation(SlaughterOperation slaughterAcid){
        return slaughterHouseDao.insertSlaughteroperartion(slaughterAcid);
    }
}
