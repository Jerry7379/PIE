package com.sjcl.zrsy.bigchaindb;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

public class CipherUtilTest {


    private String password = "justtest";

    @Test
    public void testEncrypt() throws GeneralSecurityException {
        String str = "i lvoe yout";
        byte[] code = CipherUtil.encrypt(str.getBytes(), password);
        byte[] data = CipherUtil.decrypt(code, password);
        String decrypt = new String(data);
        Assert.assertEquals(str, decrypt);
    }
}
