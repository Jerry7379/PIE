package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.domain.LogisticsReceive;
import com.sjcl.zrsy.domain.LogisticsWork;

/**
 * 物流接口
 */

public interface ILogisticsService {
    //物流接受货物
    String logisticsreception(LogisticsReceive logisticsReceive);
    //物流货物操作
    String logisticsoperation(LogisticsWork logisticsWork);
}
