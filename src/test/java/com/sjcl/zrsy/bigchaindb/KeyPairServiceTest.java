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
import java.util.Arrays;

public class KeyPairServiceTest {
//    private static KeyPair keyPair;
//
//    private static KeyPairService keyPairService;
//
//
//    private static final String password = "hellobb";
//    @BeforeClass
//    public static void prepare() {
//        keyPair = new net.i2p.crypto.eddsa.KeyPairGenerator().generateKeyPair();
//        keyPairService = new KeyPairServiceStub();
//    }

//    @Test
//    public void testSave() {
//        keyPairService.save(keyPair, password);
//        Assert.assertTrue(new File(KeyPairService.PRIKEY_FILE).exists());
//        Assert.assertTrue(new File(KeyPairService.PUBKEY_FILE).exists());
//    }
//
//    @Test
//    public void testGet() {
//        KeyPair keyPair =  keyPairService.get(password);
//        Assert.assertTrue(keyPair != null);
//        Assert.assertTrue(Arrays.equals(keyPair.getPrivate().getEncoded(), KeyPairServiceTest.keyPair.getPrivate().getEncoded()));
//        Assert.assertTrue(Arrays.equals(keyPair.getPublic().getEncoded(), KeyPairServiceTest.keyPair.getPublic().getEncoded()));
//    }
//
//    public static class KeyPairServiceStub extends KeyPairService {
//        @Override
//        protected byte[] getPubEncoded() throws IOException {
//            return keyPair.getPublic().getEncoded();
//        }
//
//        @Override
//        protected byte[] getPriEncoded(String password) throws IOException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
//            return keyPair.getPrivate().getEncoded();
//        }
//    }
//
//    @AfterClass
//    public static void clear() {
//        new File(KeyPairService.PRIKEY_FILE).delete();
//        new File(KeyPairService.PUBKEY_FILE).delete();
//    }
}
