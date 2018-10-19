package com.sjcl.zrsy.service;

/**
 * transfer servcie.
 *
 * transfer pig from one registration to next registration
 */
public interface ITransferService {

    /**
     * transfer pig to publicKeyInHex
     * @param pigId pigId
     * @param publicKeyInHex publicKeyInHex
     * @throws Exception
     */
    void transfer(String pigId, String publicKeyInHex) throws Exception;
}
