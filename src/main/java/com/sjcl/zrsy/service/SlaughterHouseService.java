package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.SlaughterHouseDao;
import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.SlaughterAcid;
import com.sjcl.zrsy.service.implement.ISlaughterHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaughterHouseService implements ISlaughterHouseService {
    @Autowired
    SlaughterHouseDao slaughterHouseDao;
    @Override
    public String slaughterreception(PigSlaughterReceiver receiver) {
        return slaughterHouseDao.slaughterreception(receiver);
    }

    @Override
    public String slaughteroperation(SlaughterAcid slaughterAcid){
        return slaughterHouseDao.slaughteroperartion(slaughterAcid);
    }
}
