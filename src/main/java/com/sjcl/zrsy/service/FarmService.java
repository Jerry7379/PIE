package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.FarmDao;
import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.FarmReception;
import com.sjcl.zrsy.service.implement.IFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmService implements IFarmService {
    @Autowired
    FarmDao farmdao;
    //养殖场接受新猪
    @Override
    public boolean farmReception(FarmReception newpig){
        return farmdao.farmreception(newpig);
    };
    //养殖场操作
    @Override
    public boolean farmOperation(FarmOperation farmOperation) {
        return farmdao.farmoperation(farmOperation);
    }
}
