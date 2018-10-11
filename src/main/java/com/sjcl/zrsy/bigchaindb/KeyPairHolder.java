package com.sjcl.zrsy.bigchaindb;

import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.Utils;

import java.security.KeyPair;

public class KeyPairHolder {
    public static java.security.KeyPair KEYPAIR;

    private static boolean isLogin = false;

    public static void setKeyPair(java.security.KeyPair keyPair) {
        KEYPAIR = keyPair;
    }

    public static KeyPair getKeyPair() {
        return KEYPAIR;
    }

    public static boolean isLogined() {
        return isLogin;
    }

    public static void login(KeyPair keyPair) {
        if (keyPair != null) {
            KEYPAIR = keyPair;
            isLogin = true;
        }
    }

    public static EdDSAPublicKey getPublic() {
        return (EdDSAPublicKey) KEYPAIR.getPublic();
    }

    public static EdDSAPrivateKey getPrivate() {
        return (EdDSAPrivateKey) KEYPAIR.getPrivate();
    }

    public static String hexstrPrivateKey() {
        return Utils.bytesToHex(KEYPAIR.getPrivate().getEncoded());
    }

    public static String hexstrPublicKey() {
        return Utils.bytesToHex(KEYPAIR.getPublic().getEncoded());
    }

}
