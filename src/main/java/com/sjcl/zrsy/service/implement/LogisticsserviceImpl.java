package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.ILogisticsOperationDao;
import com.sjcl.zrsy.dao.ITraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.LogisticsOperation;
import com.sjcl.zrsy.domain.dto.LogisticsReception;
import com.sjcl.zrsy.service.ILogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticsserviceImpl implements ILogisticsService {
    @Autowired
    ILogisticsOperationDao logisticsOperationDao;

    @Autowired
    ITraceabilityIdcardDao traceabilityIdcardDao;

    //物流接受货物
    @Override
    public boolean logisticsreception(LogisticsReception logisticsReception)  {
        return traceabilityIdcardDao.updateLogistics(logisticsReception.toLogistics());
    }


    //物流货物操作
    @Override
    public boolean logisticsoperation(LogisticsOperation logisticsOperation){
        return logisticsOperationDao.insertLogisticsOperation(logisticsOperation);

    }
}
