package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.TransferOperation;

/**
 * transfer data access object
 */
public interface ITransferDao {
    /**
     *
     * @param transferOperation
     * @throws Exception
     */
    boolean transfer(TransferOperation transferOperation) throws Exception;
}
