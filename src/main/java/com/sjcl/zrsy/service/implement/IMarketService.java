package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.MarketOperation;
import com.sjcl.zrsy.domain.MarketReception;

public interface IMarketService {
    boolean marketreception(MarketReception marketReception);

    boolean marketoperation(MarketOperation marketOperation);
}
