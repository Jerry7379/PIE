package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.SlaughterHouseDao;
import com.sjcl.zrsy.domain.SlaughterReception;
import com.sjcl.zrsy.domain.SlaughterOperation;
import com.sjcl.zrsy.service.ISlaughterHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaughterHouseServiceImpl implements ISlaughterHouseService {
    @Autowired
    SlaughterHouseDao slaughterHouseDao;
    @Override
    public boolean slaughterreception(SlaughterReception slaughterReception) {
        return slaughterHouseDao.updateSlaughterreception(slaughterReception);
    }

    @Override
    public boolean slaughteroperation(SlaughterOperation slaughterOperation){
        return slaughterHouseDao.insertSlaughteroperartion(slaughterOperation);
    }
}
