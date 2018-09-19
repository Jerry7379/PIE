package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.dao.implement.TraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.MarketReception;
import com.sjcl.zrsy.domain.po.Operation;
import com.sjcl.zrsy.service.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl implements IMarketService {
    @Autowired
    IOperationDao operationDao;

    @Autowired
    TraceabilityIdcardDao traceabilityIdcardDao;

    @Override
    public boolean marketreception(MarketReception marketReception){
        return traceabilityIdcardDao.updateMarket(marketReception.toMarket());
    }
    @Override
    public boolean marketoperation(Operation marketOperation){
        return operationDao.insertMarketOperation(marketOperation);
    }
}
