package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.MarketDao;
import com.sjcl.zrsy.domain.MarketOperation;
import com.sjcl.zrsy.domain.MarketReception;
import com.sjcl.zrsy.service.implement.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl implements IMarketService {
    @Autowired
    MarketDao marketdao;
    @Override
    public boolean marketreception(MarketReception marketReception){
        return marketdao.updateMarketReception(marketReception);
    }
    @Override
    public boolean marketoperation(MarketOperation marketOperation){
        return marketdao.insertMarketOperation(marketOperation);
    }
}
