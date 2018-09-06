package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.SlaughterOperationDao;
import com.sjcl.zrsy.dao.TraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.SlaughterReception;
import com.sjcl.zrsy.domain.dto.SlaughterOperation;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import com.sjcl.zrsy.service.ISlaughterHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaughterHouseServiceImpl implements ISlaughterHouseService {
    @Autowired
    private SlaughterOperationDao slaughterOperationDao;

    @Autowired
    private TraceabilityIdcardDao traceabilityIdcardDao;

    @Override
    public boolean slaughterreception(SlaughterReception slaughterReception) {



        return traceabilityIdcardDao.updateQuarantine(slaughterReception.toQuarantine());
    }

    @Override
    public boolean slaughteroperation(SlaughterOperation slaughterOperation){
        boolean insertOperationSuccess = slaughterOperationDao.insertSlaughteroperartion(slaughterOperation);
        TraceabilityIdcard acidInfo = slaughterOperation.toAcid();
        boolean updateIdcardSuccess = traceabilityIdcardDao.updateAcid(acidInfo);
        return insertOperationSuccess && updateIdcardSuccess;
    }
}
