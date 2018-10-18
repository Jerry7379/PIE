package com.sjcl.zrsy.dao;

public interface ITransferDao {
    void transfer(String pigId, String publicKeyInHex) throws Exception;
}
