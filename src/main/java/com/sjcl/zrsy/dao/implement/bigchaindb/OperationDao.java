package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.alibaba.fastjson.JSONObject;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Transactions;
import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.ILogisticsOperationDao;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.domain.dto.LogisticsOperation;
import com.sjcl.zrsy.domain.dto.MetaData;
import com.sjcl.zrsy.domain.po.Operation;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class OperationDao implements IOperationDao, ILogisticsOperationDao {
    public static final String OPERATION_OPERATION = "operation";
    public static final String OPERATION_TRACEABILLITYIDCARD = "traceabillityidcard";

    /**
     * 养殖场增加操作
     *
     * @param operation
     * @return
     */
    @Override
    public boolean insertFarmOperation(Operation operation) {
        try {
            BigchaindbUtil.transferToSelf(operationMetaData(operation), operation.getId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 超市增加操作
     *
     * @param operation
     * @return
     */
    @Override
    public boolean insertMarketOperation(Operation operation) {
        try {
            BigchaindbUtil.transferToSelf(operationMetaData(operation), operation.getId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 屠宰场增加操作
     *
     * @param operation
     * @return
     */
    @Override
    public boolean insertSlaughteroperartion(Operation operation) {
        try {
            BigchaindbUtil.transferToSelf(operationMetaData(operation), operation.getId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean insertLogisticsOperation(LogisticsOperation logisticsOperation) {
        try {
            BigchaindbUtil.transferToSelf(operationMetaData(logisticsOperation), logisticsOperation.getId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获得猪的所有操作
     *
     * @param pigId
     * @return
     */
    @Override
    public List<Operation> findallOperationByPigid(String pigId) {
        try {
            return transactionAllTransfers(pigId);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 构建操作的metadata
     *
     * @param object
     * @return
     */
    private MetaData operationMetaData(Object object) {
        return new MetaData(OPERATION_OPERATION, object);
    }

    /**
     * 获得某只猪的在某场的操作
     *
     * @param role
     * @param assetid
     * @return
     * @throws IOException
     */
    private static List<Operation> transactionTransfers(String role, String assetid) throws IOException {
        List<Operation> operations = null;
        Transactions transactions = TransactionsApi.getTransactionsByAssetId(assetid, Operations.TRANSFER);
        for (int i = 0; i < transactions.getTransactions().size(); i++) {
            JSONObject jsonObject = (JSONObject) transactions.getTransactions().get(i).getMetaData();
            if (jsonObject.get("operation") != null) {
                JSONObject metaDataOperation = (JSONObject) jsonObject.get("operation");
                if (metaDataOperation.get("role").equals(role)) {
                    Operation operation = new Operation(metaDataOperation.get("id").toString(), metaDataOperation.get("operation").toString(), metaDataOperation.get("content").toString(), metaDataOperation.get("remark").toString(), metaDataOperation.get("time").toString());
                    operations.add(operation);
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return operations;
    }

    /**
     * 获得猪的全部操作
     *
     * @param assetid
     * @return
     * @throws IOException
     */
    private static List<Operation> transactionAllTransfers(String assetid) throws IOException {//
        List<Operation> operations = null;
        Transactions transactions = TransactionsApi.getTransactionsByAssetId(assetid, Operations.TRANSFER);
        for (int i = 0; i < transactions.getTransactions().size(); i++) {
            JSONObject jsonObject = (JSONObject) transactions.getTransactions().get(i).getMetaData();
            if (jsonObject.get("operation") != null) {
                JSONObject metaDataOperation = (JSONObject) jsonObject.get("operation");
                Operation operation = new Operation(metaDataOperation.get("id").toString(), metaDataOperation.get("operation").toString(), metaDataOperation.get("content").toString(), metaDataOperation.get("remark").toString(), metaDataOperation.get("time").toString());
                operations.add(operation);
            } else {
                continue;
            }
        }
        return operations;
    }

}
