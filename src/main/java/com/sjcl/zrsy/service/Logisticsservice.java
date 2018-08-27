package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.LogisticsDao;
import com.sjcl.zrsy.domain.LogisticsReceive;
import com.sjcl.zrsy.domain.LogisticsWork;
import com.sjcl.zrsy.service.implement.ILogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Logisticsservice implements ILogisticsService {
    @Autowired
    LogisticsDao logisticsDao;
    //物流接受货物
    @Override
    public String logisticsreception(LogisticsReceive logisticsReceive) {
        return logisticsDao.logisticsReception(logisticsReceive);
    }


    //物流货物操作
    @Override
    public String logisticsoperation(LogisticsWork logisticsWork){
        return logisticsDao.logisticsoperarion(logisticsWork);

    }
}
