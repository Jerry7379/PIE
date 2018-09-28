package com.sjcl.zrsy.bigchaindb;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;

public class KeyPairDaoTest {
    private static KeyPair keyPair;

    private static KeyPairDao keyPairDao;


    private static final String password = "justtestjusttest";
    @BeforeClass
    public static void prepare() {
        keyPair = new net.i2p.crypto.eddsa.KeyPairGenerator().generateKeyPair();
        System.out.println(keyPair.getPrivate().toString());
        keyPairDao = new KeyPairDao();
    }

    @Test
    public void testSave() {
        keyPairDao.save(keyPair, password);
    }

    @Test
    public void testGet() {
        KeyPair keyPair =  keyPairDao.get(password);
        System.out.println(keyPair);
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
}
