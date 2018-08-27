package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.MarketDao;
import com.sjcl.zrsy.domain.MarketREC;
import com.sjcl.zrsy.domain.MarketWork;
import com.sjcl.zrsy.service.implement.IMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService implements IMarketService {
    @Autowired
    MarketDao marketdao;
    @Override
    public String marketreception(MarketREC market_rec){
        return marketdao.marketReception(market_rec);
    }
    @Override
    public String marketoperation(MarketWork marketWork){
        return marketdao.marketOperation(marketWork);
    }
}
