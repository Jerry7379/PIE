package com.sjcl.zrsy.bigchaindb;

import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.model.Transaction;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;

import java.security.KeyPair;

/**
 * used for interactive with bigchaindb.
 */
public class BigchaindbUtil {

    /**
     * store object as asset into bigchaindb.
     * metedata recored the object's class name, use 'className' as key
     * @param object
     * @return
     */
    public static String createAsset(Object object) throws Exception {
        KeyPair keyPair = KeyPairHolder.getKeyPair();
        MetaData metaData = new MetaData();
        metaData.setMetaData("className", object.getClass().getCanonicalName());
        Transaction createTransaction = BigchainDbTransactionBuilder
                .init()
                .addAssets(object, object.getClass())
                .addMetaData(metaData)
                .operation(Operations.CREATE)
                .buildAndSign((EdDSAPublicKey) keyPair.getPublic(), (EdDSAPrivateKey) keyPair.getPrivate())
                .sendTransaction();
        return createTransaction.getId();
    }
}
