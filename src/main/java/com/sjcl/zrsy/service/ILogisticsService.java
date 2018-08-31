package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.LogisticsOperation;
import com.sjcl.zrsy.domain.LogisticsReception;

/**
 * 物流接口
 */

public interface ILogisticsService {
    //物流接受货物
    boolean logisticsreception(LogisticsReception logisticsReception);
    //物流货物操作
    boolean logisticsoperation(LogisticsOperation logisticsOperation);
}
