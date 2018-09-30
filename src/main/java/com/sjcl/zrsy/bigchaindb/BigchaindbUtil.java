package com.sjcl.zrsy.bigchaindb;


import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Asset;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.sjcl.zrsy.domain.dto.AssetData;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ClassUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * used for interactive with bigchaindb.
 */
public class BigchaindbUtil {

    public static String createAsset(AssetData assetObject) throws Exception {
        return createAsset(assetObject, null);
    }

    public static String createAsset(AssetData assetObject, Object metadateObject) throws Exception {

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

    public static AssetData getAsset(String assetId) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Transaction createTransaction = getCreateTransaction(assetId);
        if (createTransaction == null) {
            return null;
        }
        Asset asset = createTransaction.getAsset();
        com.google.gson.internal.LinkedTreeMap assetData = (LinkedTreeMap) asset.getData();

        String type = (String) assetData.get("type");
        com.google.gson.internal.LinkedTreeMap properties = (LinkedTreeMap) assetData.get("data");
        
        Class beanClass = ClassUtils.getClass(type);
        Object bean = beanClass.newInstance();
        BeanUtils.populate(bean, properties);
        return new AssetData(bean);
    }

    public static String transferToSelf(Object metaData, String assetId) throws Exception {

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

    public static Transaction getCreateTransaction(String assetId) throws IOException {
        try {
            Transactions apiTransactions = TransactionsApi.getTransactionsByAssetId(assetId, Operations.CREATE);

            List<Transaction> transactions = apiTransactions.getTransactions();
            if (transactions != null && transactions.size() == 1) {
                return transactions.get(0);
            } else {
                return null;
            }

        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static String getLastTransactonId(String assetId) throws IOException {
        List<Transaction> transfers = TransactionsApi.getTransactionsByAssetId(assetId, Operations.TRANSFER).getTransactions();

        if (transfers != null && transfers.size() > 0) {
            return transfers.get(transfers.size() - 1).getId();
        } else {
            return getCreateTransaction(assetId).getId();
        }
    }

    private static FulFill transferToSelfFulFill(String assetId) throws IOException {
        final FulFill spendFrom = new FulFill();
        String transactionId = getLastTransactonId(assetId);
        spendFrom.setTransactionId(transactionId);
        spendFrom.setOutputIndex(0);
        return spendFrom;
    }
}
