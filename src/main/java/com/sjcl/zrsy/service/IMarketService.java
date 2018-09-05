package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.po.MarketOperation;
import com.sjcl.zrsy.domain.dto.MarketReception;

public interface IMarketService {
    boolean marketreception(MarketReception marketReception);

    boolean marketoperation(MarketOperation marketOperation);
}
