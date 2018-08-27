package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.MarketREC;
import com.sjcl.zrsy.domain.MarketWork;

public interface IMarketService {
    String marketreception(MarketREC market_rec);

    String marketoperation(MarketWork marketWork);
}
