package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.MarketReception;
import com.sjcl.zrsy.domain.po.Operation;

/**
 * market service
 */
public interface IMarketService {

    /**
     * market receive a pig
     * @param marketReception receive object
     * @return <tt>true</tt> if seccess
     */
    boolean marketreception(MarketReception marketReception);

    /**
     * market operation a pig
     * @param marketOperation
     * @return <tt>true</tt> if seccess
     */
    boolean marketoperation(Operation marketOperation);
}
