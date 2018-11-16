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
    void transfer(TransferOperation transferOperation) throws Exception;
}
