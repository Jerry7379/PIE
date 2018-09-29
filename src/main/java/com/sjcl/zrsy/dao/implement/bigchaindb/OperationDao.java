package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.domain.po.Operation;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationDao implements IOperationDao {
    private static final String FARM_ROLE = "farm";
    private static final String SLAUGHTER_ROLE = "slaughter";
    private static final String MARKET_ROLE = "market";
    KeyPair keyPair= KeyPairHolder.getKeyPair();

    @Override
    public boolean insertFarmOperation(Operation operation) {
        try {
            BigchaindbUtil.transferIdCardAndOperation(operationMetaData(FARM_ROLE, operation), keyPair);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean insertMarketOperation(Operation operation) {
        try {
            BigchaindbUtil.transferIdCardAndOperation(operationMetaData(MARKET_ROLE, operation), keyPair);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean insertSlaughteroperartion(Operation operation) {
        try {
            BigchaindbUtil.transferIdCardAndOperation(operationMetaData(SLAUGHTER_ROLE, operation), keyPair);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Operation> findFarmOperationByPigid(String pigId) {
        return null;
    }

    @Override
    public List<Operation> findMarketOperationByPigid(String pigId) {
        return null;
    }

    @Override
    public List<Operation> findSlaughterOperationByPigid(String pigId) {
        return null;
    }

    @Override
    public List<Operation> findallOperationByPigid(String pigId) {
        return null;
    }

    private Map operationMetaData(String role,Operation operation){
        Map<String,Object> params=new HashMap<>();
        params.put("role",role);
        params.put("id",operation.getId());
        params.put("operation",operation.getOperation());
        params.put("content",operation.getContent());
        params.put("remark",operation.getRemark());
        params.put("time",operation.getTime());
        Map<String,Object> metaData=new HashMap<>();
        metaData.put("operation",params);
        return  metaData;
    }
}
