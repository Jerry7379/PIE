package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.LogisticsOperation;

/**
 * LogisticsOperation data access object
 */
public interface ILogisticsOperationDao {
    /**
     * insert logistics operation
     * @param logisticsOperation to be insert to database
     * @return <tt>true</tt> if inserted successfully
     */
    boolean insertLogisticsOperation(LogisticsOperation logisticsOperation);
}
