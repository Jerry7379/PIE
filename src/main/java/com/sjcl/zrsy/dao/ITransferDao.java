package com.sjcl.zrsy.dao;

/**
 * transfer data access object
 */
public interface ITransferDao {
    /**
     * transfer pig to publickeyInHex
     * @param pigId pigId
     * @param publicKeyInHex publicKeyInHex
     * @throws Exception
     */
    void transfer(String pigId, String publicKeyInHex) throws Exception;
}
