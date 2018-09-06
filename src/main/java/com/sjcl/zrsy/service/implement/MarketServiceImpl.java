package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.MarketOperationDao;
import com.sjcl.zrsy.dao.TraceabilityIdcardDao;
import com.sjcl.zrsy.domain.po.MarketOperation;
import com.sjcl.zrsy.domain.dto.MarketReception;
import com.sjcl.zrsy.service.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl implements IMarketService {
    @Autowired
    MarketOperationDao marketdao;

    @Autowired
    TraceabilityIdcardDao traceabilityIdcardDao;

    @Override
    public boolean marketreception(MarketReception marketReception){
        return traceabilityIdcardDao.updateMarket(marketReception.toMarket());
    }
    @Override
    public boolean marketoperation(MarketOperation marketOperation){
        return marketdao.insertMarketOperation(marketOperation);
    }
}
