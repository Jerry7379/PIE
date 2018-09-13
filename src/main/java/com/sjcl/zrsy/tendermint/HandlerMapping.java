package com.sjcl.zrsy.tendermint;

import org.springframework.web.servlet.HandlerExecutionChain;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    HandlerExecutionChain getHandler(Transaction tx) throws Exception;
}
