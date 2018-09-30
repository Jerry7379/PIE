package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;

import com.sjcl.zrsy.dao.ILogisticsOperationDao;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.domain.dto.LogisticsOperation;
import com.sjcl.zrsy.domain.dto.PIEMetaData;
import com.sjcl.zrsy.domain.po.Operation;

import java.io.IOException;

import java.util.List;


public class OperationDao implements IOperationDao, ILogisticsOperationDao {
    public static final String FARM_ROLE = "farm";
    public static final String SLAUGHTER_ROLE = "slaughter";
    public static final String MARKET_ROLE = "market";
    public static final String LOGISTICS_ROLE = "logistic";
    public static final String OPERATION_OPERATION = "operation";
    public static final String OPERATION_TRACEABILLITYIDCARD="traceabillityidcard";

    /**
     * 养殖场增加操作
     * @param operation
     * @return
     */
    @Override
    public boolean insertFarmOperation(Operation operation) {
        try {
            BigchaindbUtil.transferToSelf(operationMetaData(FARM_ROLE, operation),operation.getId());
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
            BigchaindbUtil.transferToSelf(operationMetaData(MARKET_ROLE, operation),operation.getId());
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
            BigchaindbUtil.transferToSelf(operationMetaData(SLAUGHTER_ROLE, operation),operation.getId());
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean insertLogisticsOperation(LogisticsOperation logisticsOperation) {
        try {
            BigchaindbUtil.transferToSelf(operationMetaData(LOGISTICS_ROLE,logisticsOperation),logisticsOperation.getId());
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
            return BigchaindbUtil.transactionTransfers(FARM_ROLE,pigId);
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
            return BigchaindbUtil.transactionTransfers(MARKET_ROLE,pigId);
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
            return BigchaindbUtil.transactionTransfers(SLAUGHTER_ROLE,pigId);
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
     * @param object
     * @return
     */
    private PIEMetaData operationMetaData(String role, Object object){
        return  new PIEMetaData(OPERATION_OPERATION,role,object);
    }


}
