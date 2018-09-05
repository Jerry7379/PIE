package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.FarmDao;
import com.sjcl.zrsy.dao.TraceabilityIdcardDao;
import com.sjcl.zrsy.domain.po.FarmOperation;
import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.service.IFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmServiceImpl implements IFarmService {

    @Autowired
    TraceabilityIdcardDao traceabilityIdcardDao;

    @Autowired
    FarmDao farmdao;
    //养殖场接受新猪
    @Override
    public boolean farmReception(FarmReception farmReception){
        return farmdao.insertFarmReception(farmReception);
    }
    //养殖场操作
    @Override
    public boolean farmOperation(FarmOperation farmOperation) {
        return farmdao.insertFarmOperation(farmOperation);
    }

    @Override
    public boolean farmCheck(String id ){
        return traceabilityIdcardDao.exsits(id);
    }
}
