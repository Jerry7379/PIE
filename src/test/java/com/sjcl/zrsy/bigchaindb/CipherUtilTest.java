package com.sjcl.zrsy.bigchaindb;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Arrays;

public class CipherUtilTest {
    @Test
    public void testCipher() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        String data = "helloworld";
        String password = "justtestjusttest";

        byte[] code = CipherUtil.encrypt(data.getBytes(), password);

        Assert.assertTrue(!Arrays.equals(data.getBytes(), code));

        String wrongPassword = "testjusttestjust";
        byte[] wrongSource = CipherUtil.decrypt(code, wrongPassword);

        Assert.assertTrue(!Arrays.equals(wrongSource, data.getBytes()));

        byte[] source = CipherUtil.decrypt(code, password);

        System.out.println(new String(source));
        Assert.assertTrue(Arrays.equals(source, data.getBytes()));
    }
}
