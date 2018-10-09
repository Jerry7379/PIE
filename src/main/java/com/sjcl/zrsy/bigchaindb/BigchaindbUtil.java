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
import com.sjcl.zrsy.domain.dto.BigchaindbData;
import com.sjcl.zrsy.domain.po.Operation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ClassUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * used for interactive with bigchaindb.
 */
public class BigchaindbUtil {


    public static String createAsset(Object assetObject) throws Exception {
        BigchaindbData assetData = new BigchaindbData(assetObject);
        return createAsset(assetData);
    }

    public static String createAsset(Object assetObject, Object metadataObject) throws Exception {
        BigchaindbData assetData = new BigchaindbData(assetObject);
        BigchaindbData metaData = new BigchaindbData(metadataObject);
        return createAsset(assetData, metaData);
    }

    public static String createAsset(BigchaindbData assetWrapper) throws Exception {
        return createAsset(assetWrapper, null);
    }

    public static String createAsset(BigchaindbData assetWrapper, BigchaindbData metadataWrapper) throws Exception {

        Transaction createTransaction = BigchainDbTransactionBuilder
                .init()
                .operation(Operations.CREATE)
                .addAssets(assetWrapper, assetWrapper.getClass())
                .addMetaData(metadataWrapper)
                .buildAndSign(
                        KeyPairHolder.getPublic(),
                        KeyPairHolder.getPrivate())
                .sendTransaction();
        return createTransaction.getId();
    }

    public static Object getAsset(String assetId) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Transaction createTransaction = getCreateTransaction(assetId);
        if (createTransaction == null) {
            return null;
        }
        Asset asset = createTransaction.getAsset();
        com.google.gson.internal.LinkedTreeMap assetData = (LinkedTreeMap) asset.getData();

        return bigchaindbDataToBean(assetData);
    }

    public static <T> T getAsset(String assetId, Class<T> type) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Objects.requireNonNull(type);

        Transaction createTransaction = getCreateTransaction(assetId);
        if (createTransaction == null) {
            return null;
        }
        Asset asset = createTransaction.getAsset();
        com.google.gson.internal.LinkedTreeMap assetData = (LinkedTreeMap) asset.getData();

        String typeName = (String) assetData.get("type");
        if (!type.getCanonicalName().equals(typeName)) {
            return null;
        }


        com.google.gson.internal.LinkedTreeMap properties = (LinkedTreeMap) assetData.get("data");

        T bean = type.newInstance();
        BeanUtils.populate(bean, properties);
        return bean;
    }

    public static Object bigchaindbDataToBean(LinkedTreeMap bigchaindbData) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String type = (String) bigchaindbData.get("type");
        com.google.gson.internal.LinkedTreeMap properties = (LinkedTreeMap) bigchaindbData.get("data");

        Class beanClass = ClassUtils.getClass(type);
        Object bean = beanClass.newInstance();
        BeanUtils.populate(bean, properties);
        return bean;
    }

    public static boolean assetIsExist(String assetId) {
        try {
            Object asset = getAsset(assetId);
            return asset != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static <T> boolean assetIsExist(String assetId, Class<T> type) {
        try {
            T asset = getAsset(assetId, type);
            return asset != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static String transferToSelf(BigchaindbData metaData, String assetId) throws Exception {

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

    public static Transactions getTransactionsByAssetId(String assetId) throws IOException {
        Transactions transactions = new Transactions();
        Transactions createTransactions = TransactionsApi.getTransactionsByAssetId(assetId, Operations.CREATE);
        for (Transaction create : createTransactions.getTransactions()) {
            transactions.addTransaction(create);
        }
        Transactions transfers = TransactionsApi.getTransactionsByAssetId(assetId, Operations.TRANSFER);
        for (Transaction transfer : transfers.getTransactions()) {
            transactions.addTransaction(transfer);
        }
        return transactions;
    }

    public static String getLastTransactonId(String assetId) throws IOException {
        List<Transaction> transfers = TransactionsApi.getTransactionsByAssetId(assetId, Operations.TRANSFER).getTransactions();

        if (transfers != null && transfers.size() > 0) {
            return transfers.get(transfers.size() - 1).getId();
        } else {
            return getCreateTransaction(assetId).getId();
        }
    }

    public static <T> T getWholeMetaData(String assetId, Class<T> type) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, IntrospectionException {

        List<T> metadatas = getMetaDatas(assetId, type);
        if (CollectionUtils.isEmpty(metadatas)) {
            return null;
        }

        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        T bean = type.newInstance();
        for (T data : metadatas) {
            for (PropertyDescriptor pd : pds) {
                if (pd.getWriteMethod() == null || pd.getWriteMethod() == null) {
                    continue;
                }
                Object retVal = pd.getReadMethod().invoke(data);
                if (retVal != null) {
                    pd.getWriteMethod().invoke(bean, retVal);
                }
            }
        }
        return bean;
    }

    public static <T> List<T> getMetaDatas(String assetId, Class<T> type) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<T> metadatas = new ArrayList<>();

        Transactions transactions = getTransactionsByAssetId(assetId);
        for (Transaction transaction : transactions.getTransactions()) {
            LinkedTreeMap metaDataMap = (LinkedTreeMap) transaction.getMetaData();
            if (metaDataMap != null) {
                Object bean = BigchaindbUtil.bigchaindbDataToBean(metaDataMap);
                if (bean != null && type.isInstance(bean)) {
                    metadatas.add((T) bean);
                }
            }
        }
        return metadatas;
    }

    private static FulFill transferToSelfFulFill(String assetId) throws IOException {
        final FulFill spendFrom = new FulFill();
        String transactionId = getLastTransactonId(assetId);
        spendFrom.setTransactionId(transactionId);
        spendFrom.setOutputIndex(0);
        return spendFrom;
    }
}
