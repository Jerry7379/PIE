package com.sjcl.zrsy.service;

import com.sjcl.zrsy.dao.LogisticsDao;
import com.sjcl.zrsy.domain.LogisticsReception;
import com.sjcl.zrsy.domain.LogisticsOperation;
import com.sjcl.zrsy.service.implement.ILogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticsserviceImpl implements ILogisticsService {
    @Autowired
    LogisticsDao logisticsDao;
    //物流接受货物
    @Override
    public boolean logisticsreception(LogisticsReception logisticsReception) {
        return logisticsDao.updateLogisticsReception(logisticsReception);
    }


    //物流货物操作
    @Override
    public boolean logisticsoperation(LogisticsOperation logisticsOperation){
        return logisticsDao.insertLogisticsoperarion(logisticsOperation);

    }
}
