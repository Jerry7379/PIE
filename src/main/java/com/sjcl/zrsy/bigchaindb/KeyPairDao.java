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
    public static final String PRIKEY_FILE = "keystore_prikey.ks";
    public static final String PUBKEY_FILE = "keystore_pubkey.ks";

    private static final String KEYFACTORY_ALGORITHM = "EdDSA";
    private static final String EDDSA_PARAMETER_SPEC_NAME = "ed25519";

    public KeyPairDao() {
    }

    public boolean save(KeyPair keyPair, String password) {

        try {
            try(FileOutputStream priKeyOut = new FileOutputStream(PRIKEY_FILE)) {
                byte[] priKeyCode =  CipherUtil.encrypt(keyPair.getPrivate().getEncoded(), password);
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
        return CipherUtil.decrypt(priEncryptEncoded, password);
    }

    // protected for unit test
    protected byte[] getEncryptPriEncoded() throws IOException {
        return readBytes(PRIKEY_FILE);
    }

    // protected for unit test
    protected byte[] getPubEncoded() throws IOException {
        return readBytes(PUBKEY_FILE);
    }

    private static byte[] readBytes(String filename) throws IOException {
        try (FileInputStream in = new FileInputStream(filename)) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            return bytes;
        }
    }

    private PrivateKey deserializePriKey(byte[] priEncoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance(KEYFACTORY_ALGORITHM);
        EdDSAPrivateKeySpec priKeySpec = new EdDSAPrivateKeySpec(priEncoded, EdDSANamedCurveTable.getByName(EDDSA_PARAMETER_SPEC_NAME));
        return kf.generatePrivate(priKeySpec);
    }

    private PublicKey deserializePubKey(byte[] pubEncoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("EdDSA");
        EdDSAPublicKeySpec pubKeySpec = new EdDSAPublicKeySpec(pubEncoded, EdDSANamedCurveTable.getByName(EDDSA_PARAMETER_SPEC_NAME));
        return kf.generatePublic(pubKeySpec);
    }
}
