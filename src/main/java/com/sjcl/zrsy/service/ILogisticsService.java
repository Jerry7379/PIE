package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.LogisticsOperation;
import com.sjcl.zrsy.domain.dto.LogisticsReception;

/**
 * logistics service
 */

public interface ILogisticsService {
    /**
     * logistics receive a pig
     * @param logisticsReception receive object
     * @return <tt>true</tt> if seccess
     */
    boolean logisticsreception(LogisticsReception logisticsReception);

    /**
     * logistics operation a pig
     * @param logisticsOperation
     * @return <tt>true</tt> if seccess
     */
    boolean logisticsoperation(LogisticsOperation logisticsOperation);
}
