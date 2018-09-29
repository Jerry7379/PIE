package com.sjcl.zrsy.bigchaindb;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;

public class KeyPairDaoTest {
    private static KeyPair keyPair;

    private static KeyPairDao keyPairDao;


    private static final String password = "hellobb";
    @BeforeClass
    public static void prepare() {
        keyPair = new net.i2p.crypto.eddsa.KeyPairGenerator().generateKeyPair();
        keyPairDao = new KeyPairDaoStub();
    }

    @Test
    public void testSave() {
        keyPairDao.save(keyPair, password);
        Assert.assertTrue(new File(KeyPairDao.PRIKEY_FILE).exists());
        Assert.assertTrue(new File(KeyPairDao.PUBKEY_FILE).exists());
    }

    @Test
    public void testGet() {
        KeyPair keyPair =  keyPairDao.get(password);
        Assert.assertTrue(keyPair != null);
    }

    public static class KeyPairDaoStub extends KeyPairDao {
        @Override
        protected byte[] getPubEncoded() throws IOException {
            return keyPair.getPublic().getEncoded();
        }

        @Override
        protected byte[] getPriEncoded(String password) throws IOException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
            return keyPair.getPrivate().getEncoded();
        }
    }

    @AfterClass
    public static void clear() {
        new File(KeyPairDao.PRIKEY_FILE).delete();
        new File(KeyPairDao.PUBKEY_FILE).delete();
    }
}
