package com.sjcl.zrsy.bigchaindb;


import net.i2p.crypto.eddsa.KeyPairGenerator;
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;

public final class KeyPairDao {
    private static final String PRIKEY_FILE = "keystore_prikey.ks";
    private static final String PUBKEY_FILE = "keystore_pubkey.ks";

    private static final String ALGORITHM = "AES";

    private static final String ALGORITHM_STR =  ALGORITHM + "/ECB/PKCS5Padding";


    private static final Cipher cipher;

    static {
        try {
            cipher = Cipher.getInstance(ALGORITHM_STR);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("no such algorithm: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new Error("no such padding: " + e.getMessage());
        }
    }

    public KeyPairDao() {
    }

    public boolean save(KeyPair keyPair, String password) {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), ALGORITHM);
        try {
            // prikey
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            PrivateKey priKey = keyPair.getPrivate();
            cipher.update(priKey.getEncoded());

            byte[] encoded = cipher.doFinal();

            try(FileOutputStream priKeyOut = new FileOutputStream(PRIKEY_FILE)) {
                priKeyOut.write(encoded);
            }


            // pubkey
            PublicKey pubKey = keyPair.getPublic();
            try (FileOutputStream pubKeyOut = new FileOutputStream(PUBKEY_FILE)){
                pubKeyOut.write(pubKey.getEncoded());
            }
            return true;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public KeyPair get(String password) {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), ALGORITHM);
        try {

            PrivateKey privateKey;
            try (FileInputStream priKeyIn = new FileInputStream(PRIKEY_FILE)) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encodedPriKeyCode = new byte[priKeyIn.available()];
                priKeyIn.read(encodedPriKeyCode);
                byte[] priKeyCode = cipher.doFinal(encodedPriKeyCode);

                EdDSAPrivateKeySpec priKeySpec = new EdDSAPrivateKeySpec();

                privateKey = (PrivateKey) PrivateKeyFactory.createKey(priKeyCode);
            }

            PublicKey publicKey;
            try (FileInputStream pubKeyIn = new FileInputStream(PUBKEY_FILE)){
                byte[] pubKeyCode = new byte[pubKeyIn.available()];
                pubKeyIn.read(pubKeyCode);

                publicKey = (PublicKey) PublicKeyFactory.createKey(pubKeyCode);
            }
            return new KeyPair(publicKey, privateKey);
        } catch (InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
