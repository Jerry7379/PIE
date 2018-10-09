package com.sjcl.zrsy.bigchaindb;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class CipherUtil {
    private static final byte[] SALT = new byte[]{2, 55, 11, 127, 66, 110, 77, 3};
    private static final String CIPHER_ALGORITHM_STR = "PBEWITHMD5andDES";
    public static final int ITERATION_COUNT = 100;

    private static final Cipher cipher;

    private CipherUtil() {
    }

    static {
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_STR);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("no such algorithm: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new Error("no such padding: " + e.getMessage());
        }
    }


    private static SecretKey toKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(CIPHER_ALGORITHM_STR);
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        return secretKey;
    }

    public static byte[] encrypt(byte[] source, String password) throws GeneralSecurityException {
        SecretKey secretKey = toKey(password);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new PBEParameterSpec(SALT, ITERATION_COUNT));
        return cipher.doFinal(source);
    }

    public static byte[] decrypt(byte[] dest, String password) throws GeneralSecurityException {
        SecretKey secretKey = toKey(password);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new PBEParameterSpec(SALT, ITERATION_COUNT));
        return cipher.doFinal(dest);
    }
}
