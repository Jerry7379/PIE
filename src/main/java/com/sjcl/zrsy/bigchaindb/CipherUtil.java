package com.sjcl.zrsy.bigchaindb;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class CipherUtil {
    private static final String CIPHER_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM_STR =  CIPHER_ALGORITHM + "/ECB/PKCS5Padding";

    private static final Cipher cipher;
    public static final int PASSWD_LEN = 16;

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
        SecretKey secretKey = new SecretKeySpec(to16byte(password), CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        cipher.update(source);

        return cipher.doFinal();
    }

    public static byte[] decrypt(byte[] dest, String password) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(to16byte(password), CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(dest);
    }

    private static byte[] to16byte(String password) {
        if (password.length() == PASSWD_LEN) {
            return password.getBytes();
        }

        int pwdLen = password.length();
        String password16Byte = new String(new char[PASSWD_LEN - pwdLen]).replace("\0", "0").concat(password);
        return password16Byte.getBytes();
    }
}
