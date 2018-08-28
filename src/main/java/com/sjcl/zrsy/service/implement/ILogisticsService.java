package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.LogisticsOperation;
import com.sjcl.zrsy.domain.LogisticsReceive;

/**
 * 物流接口
 */

public interface ILogisticsService {
    //物流接受货物
    boolean logisticsreception(LogisticsReceive logisticsReceive);
    //物流货物操作
    boolean logisticsoperation(LogisticsOperation logisticsWork);
}
