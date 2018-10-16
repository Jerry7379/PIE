package com.sjcl.zrsy.service;

public interface ITransferService {
    boolean isNext(String registrationId);
    void transfer(String pigId, String registrationId) throws Exception;
}
