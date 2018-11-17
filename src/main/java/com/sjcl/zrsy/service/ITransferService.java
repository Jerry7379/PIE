package com.sjcl.zrsy.service;

import com.sjcl.zrsy.domain.dto.TransferOperation;

/**
 * transfer servcie.
 *
 * transfer pig from one registration to next registration
 */
public interface ITransferService {

    /**
     *
     * @param transferOperation
     * @throws Exception
     */
    boolean transfer(TransferOperation transferOperation) throws Exception;
}
