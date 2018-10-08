package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.LogisticsOperation;

public interface ILogisticsOperationDao {
    /**
     * insert logistics operation
     * @param logisticsOperation
     * @return
     */
    boolean insertLogisticsOperation(LogisticsOperation logisticsOperation);
}
