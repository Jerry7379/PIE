package com.sjcl.zrsy.bigchaindb;


import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.*;
import com.bigchaindb.util.JsonUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.sjcl.zrsy.domain.dto.BigchaindbData;
import net.i2p.crypto.eddsa.EdDSAPublicKey;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ClassUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * used for interactive with bigchaindb.
 *
 * signature using keypair KeyPairHolder.getKeyPair()
 */
public class BigchaindbUtil {

    /**
     * create a asset.
     *
     * metadata is null
     *
     * @param assetObject asset
     * @return asset Id
     * @throws Exception
     */
    public static String createAsset(Object assetObject) throws Exception {
        BigchaindbData assetData = new BigchaindbData(assetObject);
        return createAsset(assetData);
    }

    /**
     * create a asset with metadata.
     * @param assetObject asset
     * @param metadataObject metadata
     * @return asset Id
     * @throws Exception
     */
    public static String createAsset(Object assetObject, Object metadataObject) throws Exception {
        BigchaindbData assetData = new BigchaindbData(assetObject);
        BigchaindbData metaData = new BigchaindbData(metadataObject);
        return createAsset(assetData, metaData);
    }

    /**
     * create a asset use assetWrapper.
     * @param assetWrapper assetWrapper
     * @return asset Id
     * @throws Exception
     */
    public static String createAsset(BigchaindbData assetWrapper) throws Exception {
        return createAsset(assetWrapper, null);
    }

    /**
     * create a asset use assetWrapper and metadataWrapper.
     * @param assetWrapper assetWrapper
     * @param metadataWrapper metadataWrapper
     * @return asset Id
     * @throws Exception
     */
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

    /**
     * obtain a asset use assetId.
     * @param assetId  aseetId
     * @return assetObject
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object getAsset(String assetId) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Transaction createTransaction = getCreateTransaction(assetId);
        if (createTransaction == null) {
            return null;
        }
        Asset asset = createTransaction.getAsset();
        com.google.gson.internal.LinkedTreeMap assetData = (LinkedTreeMap) asset.getData();

        return bigchaindbDataToBean(assetData);
    }

    /**
     * obtain a explicit type asset use assetId.
     * @param assetId assetId
     * @param type asset Type class
     * @param <T> asset Type
     * @return assetObject
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T getAsset(String assetId, Class<T> type) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Objects.requireNonNull(type);

        Transaction createTransaction = getCreateTransaction(assetId);
        if (createTransaction == null) {
            return null;
        }
        Asset asset = createTransaction.getAsset();
        com.google.gson.internal.LinkedTreeMap assetData = (LinkedTreeMap) asset.getData();


        Object bean = bigchaindbDataToBean(assetData);

        if (type.isInstance(bean)) {
            return (T) bean;
        } else {
            return null;
        }
    }

    /**
     * obtain a explicit type asset use assetId.
     * @param transactionId assetId
     * @param type asset Type class
     * @param <T> asset Type
     * @return assetObject
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T getAssetByTransactionId(String transactionId, Class<T> type) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Objects.requireNonNull(type);

        Transaction transaction = TransactionsApi.getTransactionById(transactionId);
        if (transaction == null) {
            return null;
        }
        Asset asset = transaction.getAsset();
        com.google.gson.internal.LinkedTreeMap assetData = (LinkedTreeMap) asset.getData();

        Object bean = bigchaindbDataToBean(assetData);

        if (type.isInstance(bean)) {
            return (T) bean;
        } else {
            return null;
        }
    }



    /**
     * convert bigchaindb LinkedTreeMap data to java object.
     * @param bigchaindbData
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static Object bigchaindbDataToBean(LinkedTreeMap bigchaindbData) throws ClassNotFoundException {
        String type = (String) bigchaindbData.get("type");
        Object data = bigchaindbData.get("data");
        if (data instanceof com.google.gson.internal.LinkedTreeMap) {
            com.google.gson.internal.LinkedTreeMap properties = (LinkedTreeMap) bigchaindbData.get("data");
            String json = JsonUtils.toJson(properties);
            Object bean = JsonUtils.fromJson(json, ClassUtils.getClass(type));
            return bean;
        } else {
            return data;
        }
    }

    /**
     * determine whether asset exist.
     *
     * @param assetId asset Id
     * @return whether asset exist
     */
    public static boolean assetIsExist(String assetId) {
        try {
            Object asset = getAsset(assetId);
            return asset != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * determine whether asset with specify type exist.
     * @param assetId asset Id
     * @param type type class
     * @param <T> type
     * @return
     */
    public static <T> boolean assetIsExist(String assetId, Class<T> type) {

        try {
            T asset = getAsset(assetId, type);
            return asset != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * transfer asset to youself.
     *
     * youself is KeyPairHolder.getKeyPair() representive
     * @param metaData
     * @param assetId
     * @return
     * @throws Exception
     */
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

    /**
     * transfer asset to publicKeyHexTo.
     * @param assetId
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String transferTo(String assetId, PublicKey publicKey,BigchaindbData bigchaindbData) throws Exception {

        Transaction transferTransaction = BigchainDbTransactionBuilder
                .init()
                .operation(Operations.TRANSFER)
                .addAssets(assetId, String.class)
                .addMetaData(bigchaindbData)
                .addInput(null, transferToSelfFulFill(assetId), KeyPairHolder.getPublic())
                .addOutput("1", (EdDSAPublicKey)publicKey)
                .buildAndSign(
                        KeyPairHolder.getPublic(),
                        KeyPairHolder.getPrivate())
                .sendTransaction();
        return transferTransaction.getId();
    }

    /**
     * obtain asset's create transaction
     * @param assetId
     * @return
     * @throws IOException
     */
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

    /**
     * obtain all asset's all transaction.
     * sort by transfer order
     * @param assetId asset Id
     * @return transactions
     * @throws IOException
     */
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

    /**
     * obtain last transaction of asset
     * @param assetId assetId
     * @return last transaction
     * @throws IOException
     */
    public static Transaction getLastTransaction(String assetId) throws IOException {
        List<Transaction> transfers = TransactionsApi.getTransactionsByAssetId(assetId, Operations.TRANSFER).getTransactions();

        if (transfers != null && transfers.size() > 0) {
            return transfers.get(transfers.size() - 1);
        } else {
            return getCreateTransaction(assetId);
        }
    }

    /**
     * obtain last transaction id of asset.
     * @param assetId asset Id
     * @return last transaction id
     * @throws IOException
     */
    public static String getLastTransactionId(String assetId) throws IOException {
        return getTransactionId(getLastTransaction(assetId));
    }

    /**
     * obtain whole metadata.
     *
     * integrate ont asset's all transactions's metadata into one metadataObject.
     * when duplicate attributes appear, use the first one.
     *
     * for example:
     * 1.
     * {
     *     name: 'tom'
     * }
     * 2.
     * {
     *     age: 22
     * }
     * 3.
     * {
     *     gender: 'male'
     * }
     * 4.
     * {
     *     age: 44,
     *     height: 172
     * }
     * In the end, the object is:
     * {
     *     name: 'tom',
     *     age: 22,
     *     gender: 'male',
     *     height: 172
     * }
     *
     * @param assetId asset Id
     * @param type type class
     * @param <T> type
     * @return metadataObject
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     */
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
                    if(retVal.getClass().equals(int.class)&&((int)retVal!=0)){
                        ;
                    }else {
                        pd.getWriteMethod().invoke(bean, retVal);
                    }
                }
            }
        }
        return bean;
    }

    /**
     * obtain metadatas
     * @param assetId asset Id
     * @param type type class
     * @param <T> type
     * @return metadataObjects
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
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
        String transactionId = getLastTransactionId(assetId);
        spendFrom.setTransactionId(transactionId);
        spendFrom.setOutputIndex(0);
        return spendFrom;
    }

    private static String getTransactionId(Transaction transaction) {
        String withQuotationId = transaction.getId();
        return withQuotationId.substring(1, withQuotationId.length() -1);
    }

    /**
     * use pig id get asset id
     * @param pigId
     * @return
     */
    public static String getAssetId(String pigId) {
        try {
            if(AssetsApi.getAssets(pigId).getAssets().size()!=0) {
                return AssetsApi.getAssets(pigId).getAssets().get(0).getId();
            }else{
                return null;
            }
        } catch (IOException e) {
            return null;
        }


    }
}
