package com.sjcl.zrsy.bigchaindb;


import net.i2p.crypto.eddsa.EdDSASecurityProvider;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec;

import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class KeyPairDao {
    private static final String EdDSAParameterSpecName = "ed25519";

    public static final String PRIKEY_FILE = "keystore_prikey.ks";
    public static final String PUBKEY_FILE = "keystore_pubkey.ks";

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

        try {
            try(FileOutputStream priKeyOut = new FileOutputStream(PRIKEY_FILE)) {
                byte[] priKeyCode = encryptEncoded(keyPair.getPrivate(), password);
                priKeyOut.write(priKeyCode);
            }

            try (FileOutputStream pubKeyOut = new FileOutputStream(PUBKEY_FILE)){
                byte[] pubKeyCode = encoded(keyPair.getPublic());
                pubKeyOut.write(pubKeyCode);
            }
            return true;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static byte[] encryptEncoded(Key key, String password) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        cipher.update(key.getEncoded());

        return cipher.doFinal();
    }

    private static byte[] encoded(Key key) {
        return key.getEncoded();
    }


    public KeyPair get(String password) {

        try {
            Security.addProvider(new EdDSASecurityProvider());

            byte[] priEncoded = getPriEncoded(password);
            PrivateKey privateKey = deserializePriKey(priEncoded);

            byte[] pubEncoded = getPubEncoded();
            PublicKey publicKey = deserializePubKey(pubEncoded);

            return new KeyPair(publicKey, privateKey);
        } catch (InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    // protected for unit test
    protected byte[] getPriEncoded(String password) throws IOException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        byte[] priEncryptEncoded = getEncryptPriEncoded();
        return decrypt(priEncryptEncoded, password);
    }

    // protected for unit test
    protected byte[] getEncryptPriEncoded() throws IOException {
        return readBytes(PRIKEY_FILE);
    }

    // protected for unit test
    protected byte[] getPubEncoded() throws IOException {
        return readBytes(PUBKEY_FILE);
    }

    private static byte[] decrypt(byte[] encryptEncoded, String password) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(encryptEncoded);
    }

    private static byte[] readBytes(String filename) throws IOException {
        try (FileInputStream in = new FileInputStream(filename)) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            return bytes;
        }
    }

    private PrivateKey deserializePriKey(byte[] priEncoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("EdDSA");
        EdDSAPrivateKeySpec priKeySpec = new EdDSAPrivateKeySpec(priEncoded, EdDSANamedCurveTable.getByName(EdDSAParameterSpecName));
        return kf.generatePrivate(priKeySpec);
    }

    private PublicKey deserializePubKey(byte[] pubEncoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("EdDSA");
        EdDSAPublicKeySpec pubKeySpec = new EdDSAPublicKeySpec(pubEncoded, EdDSANamedCurveTable.getByName(EdDSAParameterSpecName));
        return kf.generatePublic(pubKeySpec);
    }
}
