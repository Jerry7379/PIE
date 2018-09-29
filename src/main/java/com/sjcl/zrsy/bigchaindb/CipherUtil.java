package com.sjcl.zrsy.bigchaindb;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class CipherUtil {
    private static final String CIPHER_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM_STR =  CIPHER_ALGORITHM + "/ECB/PKCS5Padding";

    private static final Cipher cipher;

    private CipherUtil(){}

    static {
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_STR);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("no such algorithm: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new Error("no such padding: " + e.getMessage());
        }
    }

    public static byte[] encrypt(byte[] source, String password) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        cipher.update(source);

        return cipher.doFinal();
    }

    public static byte[] decrypt(byte[] dest, String password) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(dest);
    }
}
