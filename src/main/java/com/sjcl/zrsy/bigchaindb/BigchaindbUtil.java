package com.sjcl.zrsy.bigchaindb;


import com.alibaba.fastjson.JSONObject;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.*;
import com.sjcl.zrsy.domain.po.Operation;

import java.io.IOException;


import java.util.List;

import com.bigchaindb.api.TransactionsApi;

/**
 * used for interactive with bigchaindb.
 */
public class BigchaindbUtil {

    public static String createAsset(Object assetObject) throws Exception {
        return createAsset(assetObject, null);
    }

    public static String createAsset(Object assetObject, Object metadateObject) throws Exception {

        Transaction createTransaction = BigchainDbTransactionBuilder
                .init()
                .operation(Operations.CREATE)
                .addAssets(assetObject, assetObject.getClass())
                .addMetaData(metadateObject)
                .buildAndSign(
                        KeyPairHolder.getPublic(),
                        KeyPairHolder.getPrivate())
                .sendTransaction();
        return createTransaction.getId();
    }

    public static String transferIdCardAndOperation(Object metaData, String assetId) throws Exception {

        Transaction transferTransaction = BigchainDbTransactionBuilder
                .init()
                .operation(Operations.TRANSFER)
                .addAssets(assetId, String.class)
                .addMetaData(metaData)
                .addInput(null, transferToSelfFulFill(assetId), KeyPairHolder.getPublic())
                .addOutput("1", KeyPairHolder.getPublic())
                .buildAndSign(
                        KeyPairHolder.getPublic(),
                        KeyPairHolder.getPrivate())
                .sendTransaction();
        return transferTransaction.getId();
    }

    public static String getLastTransactonId(String assetId) throws IOException {
        List<Transaction> transfers = TransactionsApi.getTransactionsByAssetId(assetId, Operations.TRANSFER).getTransactions();

        if (transfers != null && transfers.size() > 0) {
            return transfers.get(transfers.size() - 1).getId();
        } else {
            List<Transaction> creates = TransactionsApi.getTransactionsByAssetId(assetId, Operations.CREATE).getTransactions();
            if (creates != null && creates.size() > 0) {
                return creates.get(0).getId();
            } else {
                return null;
            }
        }
    }

    private static FulFill transferToSelfFulFill(String assetId) throws IOException {
        final FulFill spendFrom = new FulFill();
        String transactionId = getLastTransactonId(assetId);
        spendFrom.setTransactionId(transactionId);
        spendFrom.setOutputIndex(0);
        return spendFrom;
    }
    
    /**
     * 获得某只猪的在某场的操作
     *
     * @param role
     * @param assetid
     * @return
     * @throws IOException
     */
    public static List<Operation> transactionTransfers(String role, String assetid) throws IOException {
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
    public static List<Operation> transactionAllTransfers(String assetid) throws IOException {//
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
