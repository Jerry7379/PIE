package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;
import com.google.gson.internal.LinkedTreeMap;
import com.sjcl.zrsy.bigchaindb.BigchaindbUtil;
import com.sjcl.zrsy.dao.ILogisticsOperationDao;
import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.domain.dto.BigchaindbData;
import com.sjcl.zrsy.domain.dto.LogisticsOperation;
import com.sjcl.zrsy.domain.po.Operation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationDao implements IOperationDao, ILogisticsOperationDao {
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
            List<Operation> operations = new ArrayList<>();
            Transactions transactions = TransactionsApi.getTransactionsByAssetId(pigId, Operations.TRANSFER);

            for (Transaction transaction : transactions.getTransactions()) {
                LinkedTreeMap metaDataMap = (LinkedTreeMap) transaction.getMetaData();
                Object metadata = BigchaindbUtil.bigchaindbDataToBean(metaDataMap);
                if (metadata instanceof Operation) {
                    Operation operation = (Operation) metadata;
                    operations.add(operation);
                }
            }
            return operations;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 构建操作的metadata
     *
     * @param object
     * @return
     */
    private BigchaindbData operationMetaData(Object object) {
        return new BigchaindbData(object);
    }

}
