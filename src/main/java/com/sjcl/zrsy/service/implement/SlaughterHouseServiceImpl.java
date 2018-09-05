package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.SlaughterOperationDao;
import com.sjcl.zrsy.dao.TraceabilityIdcardDao;
import com.sjcl.zrsy.domain.dto.SlaughterReception;
import com.sjcl.zrsy.domain.po.SlaughterOperation;
import com.sjcl.zrsy.service.ISlaughterHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaughterHouseServiceImpl implements ISlaughterHouseService {
    @Autowired
    SlaughterOperationDao slaughterOperationDao;

    @Autowired
    TraceabilityIdcardDao traceabilityIdcardDao;

    @Override
    public boolean slaughterreception(SlaughterReception slaughterReception) {
        return traceabilityIdcardDao.updateSlaughterreception(slaughterReception);
    }

    @Override
    public boolean slaughteroperation(SlaughterOperation slaughterOperation){
        return slaughterOperationDao.insertSlaughteroperartion(slaughterOperation);
    }
}
