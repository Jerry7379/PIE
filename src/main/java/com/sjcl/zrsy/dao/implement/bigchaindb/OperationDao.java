package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.dao.ILogisticsOperationDao;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.domain.dto.LogisticsOperation;
import com.sjcl.zrsy.domain.po.Operation;

import java.io.IOException;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationDao implements IOperationDao, ILogisticsOperationDao {
    private static final String FARM_ROLE = "farm";
    private static final String SLAUGHTER_ROLE = "slaughter";
    private static final String MARKET_ROLE = "market";
    private static final String LOGISTICS_ROLE = "logistic";
    KeyPair keyPair= KeyPairHolder.getKeyPair();

    /**
     * 养殖场增加操作
     * @param operation
     * @return
     */
    @Override
    public boolean insertFarmOperation(Operation operation) {
        try {
            BigchaindbUtil.transferIdCardAndOperation(operationMetaData(FARM_ROLE, operation), keyPair);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 超市增加操作
     * @param operation
     * @return
     */
    @Override
    public boolean insertMarketOperation(Operation operation) {
        try {
            BigchaindbUtil.transferIdCardAndOperation(operationMetaData(MARKET_ROLE, operation), keyPair);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 屠宰场增加操作
     * @param operation
     * @return
     */
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
    public boolean insertLogisticsOperation(LogisticsOperation logisticsOperation) {
        Map<String,Object> params=new HashMap<>();
        params.put("role",LOGISTICS_ROLE);
        params.put("id",logisticsOperation.getId());
        params.put("carid",logisticsOperation.getCarId());
        params.put("CO2",logisticsOperation.getCo2());
        params.put("humidity",logisticsOperation.getHumidity());
        params.put("location",logisticsOperation.getLocation());
        params.put("temperature",logisticsOperation.getTemperature());
        params.put("transporttime",logisticsOperation.getTransportTime());
        Map<String,Object> metaData=new HashMap<>();
        metaData.put("operation",params);
        try {
            BigchaindbUtil.transferIdCardAndOperation(metaData, keyPair);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 获得猪的养殖场操作
     * @param pigId
     * @return
     */
    @Override
    public List<Operation> findFarmOperationByPigid(String pigId) {
        try {
            return BigchaindbUtil.transactionTransfers("farm",pigId);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获得猪的超市操作
     * @param pigId
     * @return
     */
    @Override
    public List<Operation> findMarketOperationByPigid(String pigId) {
        try {
            return BigchaindbUtil.transactionTransfers("market",pigId);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获得猪的屠宰场操作
     * @param pigId
     * @return
     */
    @Override
    public List<Operation> findSlaughterOperationByPigid(String pigId) {
        try {
            return BigchaindbUtil.transactionTransfers("slaughter",pigId);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获得猪的所有操作
     * @param pigId
     * @return
     */
    @Override
    public List<Operation> findallOperationByPigid(String pigId) {
        try {
            return BigchaindbUtil.transactionAllTransfers(pigId);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 构建操作的metadata
     * @param role
     * @param operation
     * @return
     */
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
