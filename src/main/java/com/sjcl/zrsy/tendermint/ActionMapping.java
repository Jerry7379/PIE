package com.sjcl.zrsy.tendermint;

public interface ActionMapping {
    Action getHandler(String action) throws Exception;
}
