package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.Operation;

import java.util.List;

public interface IOperationDao {
    boolean insertFarmOperation(Operation operation);

    boolean insertMarketOperation(Operation operation);

    boolean insertSlaughteroperartion(Operation operation);

    List<Operation> findallOperationByPigid(String pigId);
}
