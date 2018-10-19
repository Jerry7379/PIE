package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.Operation;

import java.util.List;

/**
 * Operation data access object
 */
public interface IOperationDao {
    /**
     * insert farm operation
     * @param operation farmOperation
     * @return <tt>true</tt> if inserted successfully
     */
    boolean insertFarmOperation(Operation operation);

    /**
     * insert market operation
     * @param operation marketOperation
     * @return <tt>true</tt> if inserted successfully
     */
    boolean insertMarketOperation(Operation operation);

    /**
     * insert farm slaughter operation
     * @param operation slaughterOperation
     * @return <tt>true</tt> if inserted successfully
     */
    boolean insertSlaughteroperartion(Operation operation);

    /**
     * find all operation by PigId
     * @param pigId pigId
     * @return operations
     */
    List<Operation> findallOperationByPigid(String pigId);
}
