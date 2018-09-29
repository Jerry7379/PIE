package com.sjcl.zrsy.bigchaindb;


import com.alibaba.fastjson.JSONObject;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.*;
import com.sjcl.zrsy.domain.po.Operation;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;

import java.io.IOException;
import java.security.KeyPair;

import java.util.List;
import java.util.Map;

import static com.bigchaindb.api.AssetsApi.getAssets;
import static com.bigchaindb.api.TransactionsApi.getTransactionsByAssetId;

/**
 * used for interactive with bigchaindb.
 */
public class BigchaindbUtil {

    /**
     * store object as asset into bigchaindb.
     * metedata recored the object's class name, use 'className' as key
     * @param assetObject,metadateObject,keyPair
     * @return
     */
    public static String createAsset(Object assetObject,Object metadateObject,KeyPair keyPair) throws Exception {

        Transaction createTransaction = BigchainDbTransactionBuilder
                .init()
                .addAssets(assetObject, assetObject.getClass())
                .addMetaData(metadateObject)
                .operation(Operations.CREATE)
                .buildAndSign((EdDSAPublicKey) keyPair.getPublic(), (EdDSAPrivateKey) keyPair.getPrivate())
                .sendTransaction();
        return createTransaction.getId();
    }

    /**
     * 发送交易，不包括猪的所有权转让
     * @param metaData
     * @param keyPair
     * @return
     * @throws Exception
     */
    public static String transferIdCardAndOperation(Map metaData, KeyPair keyPair ) throws Exception {
        Object object=metaData.get("id");
        String assetId=getAssets(object.toString()).getAssets().get(0).getId();

        Transaction transferTransaction = BigchainDbTransactionBuilder
                .init()
                .addMetaData(metaData)
                // source keypair is used in the input, because the current owner is "spending" the output to transfer it
                .addInput(null, transferInput(assetId), (EdDSAPublicKey) keyPair.getPublic())

                // after this transaction, the target 'owns' the asset, so, the new output includes the target's public key
                //第一个参数为交易的费用，github官网的例子写错了，output换为amount
                .addOutput("1", (EdDSAPublicKey) keyPair.getPublic())

                // reference the asset by ID when doing a transfer
                //资产id作为
                .addAssets(assetId, String.class)
                .operation(Operations.TRANSFER)

                // the source key signs the transaction to authorize the transfer
                .buildAndSign((EdDSAPublicKey) keyPair.getPublic(), (EdDSAPrivateKey) keyPair.getPrivate())
                .sendTransaction();
        return transferTransaction.getId();
    }

    /**
     * 获得transfer的input
     * @param assetId
     * @return
     * @throws IOException
     */
    public static FulFill transferInput(String assetId) throws IOException {
        final FulFill spendFrom = new FulFill();
        spendFrom.setOutputIndex(0);
        int size=getTransactionsByAssetId(assetId,Operations.TRANSFER).getTransactions().size();
        if(size==0) {
            spendFrom.setTransactionId(assetId);
        }else{
            String lastTransactionId=getTransactionsByAssetId(assetId,Operations.TRANSFER).getTransactions().get(size-1).getId();
            spendFrom.setTransactionId(lastTransactionId);
        }
        return spendFrom;
    }

    /**
     *获得某只猪的在某场的操作
     * @param role
     * @param pigid
     * @return
     * @throws IOException
     */
    public static List<Operation> transactionTransfers(String role,String pigid) throws IOException {
        List<Operation> operations = null;
        String assetid=getAssets(pigid).getAssets().get(0).getId();
        Transactions transactions=getTransactionsByAssetId(assetid,Operations.TRANSFER);
        for (int i=0;i<transactions.getTransactions().size();i++) {
            JSONObject jsonObject= (JSONObject)transactions.getTransactions().get(i).getMetaData();
            if(jsonObject.get("operation")!=null){
                JSONObject metaDataOperation=(JSONObject)jsonObject.get("operation");
                if(metaDataOperation.get("role").equals(role)) {
                    Operation operation = new Operation(metaDataOperation.get("id").toString(),metaDataOperation.get("operation").toString(),metaDataOperation.get("content").toString(),metaDataOperation.get("remark").toString(),metaDataOperation.get("time").toString());
                    operations.add(operation);
                }
                else{
                    continue;
                }
            }
            else{
                continue;
            }
        }
        return operations;
    }

    /**
     * 获得猪的全部操作
     * @param pigid
     * @return
     * @throws IOException
     */
    public static List<Operation> transactionAllTransfers(String pigid) throws IOException {
        List<Operation> operations = null;
        String assetid=getAssets(pigid).getAssets().get(0).getId();
        Transactions transactions=getTransactionsByAssetId(assetid,Operations.TRANSFER);
        for (int i=0;i<transactions.getTransactions().size();i++) {
            JSONObject jsonObject= (JSONObject)transactions.getTransactions().get(i).getMetaData();
            if(jsonObject.get("operation")!=null){
                JSONObject metaDataOperation=(JSONObject)jsonObject.get("operation");
                    Operation operation = new Operation(metaDataOperation.get("id").toString(),metaDataOperation.get("operation").toString(),metaDataOperation.get("content").toString(),metaDataOperation.get("remark").toString(),metaDataOperation.get("time").toString());
                    operations.add(operation);
            }
            else{
                continue;
            }
        }
        return operations;
    }
}
