package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.OperationDao;
import com.sjcl.zrsy.dao.TraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.service.IFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmServiceImpl implements IFarmService {

    @Autowired
    TraceabilityIdcardDao traceabilityIdcardDao;

    @Autowired
    OperationDao operationDao;
    //养殖场接受新猪
    @Override
    public boolean farmReception(FarmReception farmReception){
        return traceabilityIdcardDao.insert(farmReception.toFarm());
    }
    //养殖场操作
    @Override
    public boolean farmOperation(Operation operation) {
        return operationDao.insertFarmOperation(operation);
    }

    @Override
    public boolean idCardExists(String id ){
        return traceabilityIdcardDao.exsits(id);
    }
}
