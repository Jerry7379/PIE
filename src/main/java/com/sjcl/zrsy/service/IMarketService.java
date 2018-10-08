package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.MarketReception;
import com.sjcl.zrsy.domain.po.Operation;

public interface IMarketService {
    boolean marketreception(MarketReception marketReception);

    boolean marketoperation(Operation marketOperation);
}
