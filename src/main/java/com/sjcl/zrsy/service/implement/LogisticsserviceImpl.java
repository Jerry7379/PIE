package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.LogisticsDao;
import com.sjcl.zrsy.domain.dto.LogisticsReception;
import com.sjcl.zrsy.domain.po.LogisticsOperation;
import com.sjcl.zrsy.service.ILogisticsService;
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
