package com.sjcl.zrsy.bigchaindb;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Arrays;

public class CipherUtilTest {

    private byte[] data = {104, 101, 108, 108, 111, 119, 111, 114, 108, 100};
    private byte[] expectedCode = {-29, -80, 44, 85, -81, 18, 76, 17, 87, 123, -80, 115, 46, 73, 33, 71};

    private String password = "justtest";
    private String wrongPassword = "testjust";

    @Test
    public void testEncrypt() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        byte[] code = CipherUtil.encrypt(data, password);
        System.out.println(Arrays.toString(code));
        Assert.assertTrue(Arrays.equals(expectedCode, code));
    }

    @Test(expected = BadPaddingException.class)
    public void testDecrypt_wrongPassword_() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        byte[] wrongSource = CipherUtil.decrypt(expectedCode, wrongPassword);
        Assert.assertTrue(!Arrays.equals(wrongSource, data));
    }

    @Test
    public void testDecrypt_increctPassword_() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        byte[] source = CipherUtil.decrypt(expectedCode, password);
        Assert.assertTrue(Arrays.equals(source, data));
    }




}
