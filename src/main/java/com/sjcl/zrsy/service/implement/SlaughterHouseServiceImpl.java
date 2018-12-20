package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.dao.ITraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.SlaughterOperation;
import com.sjcl.zrsy.domain.dto.SlaughterReception;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import com.sjcl.zrsy.service.ISlaughterHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaughterHouseServiceImpl implements ISlaughterHouseService {
    @Autowired
    private IOperationDao operationDao;

    @Autowired
    private ITraceabilityIdcardDao traceabilityIdcardDao;

    @Override
    public boolean slaughterreception(SlaughterReception slaughterReception) {

        return traceabilityIdcardDao.updateQuarantine(slaughterReception.toQuarantine());
    }

    @Override
    public boolean slaughteroperation(SlaughterOperation slaughterOperation){
        boolean insertOperationSuccess = operationDao.insertSlaughteroperartion(slaughterOperation.toOperation());
        boolean updateIdcardSuccess=true;
        if(slaughterOperation.getOperation().equals("排酸")) {
            TraceabilityIdcard acidInfo = slaughterOperation.toAcid();
            updateIdcardSuccess = traceabilityIdcardDao.updateAcid(acidInfo);
        }
        return insertOperationSuccess && updateIdcardSuccess;
    }
}
