package com.sjcl.zrsy.bigchaindb;

import com.bigchaindb.util.KeyPairUtils;
import com.sjcl.zrsy.domain.po.Registration;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.Utils;

import java.security.KeyPair;

public class KeyPairHolder {
    public static java.security.KeyPair KEYPAIR;

    private static boolean isLogin = false;

    private static Registration user;

    public static void setKeyPair(java.security.KeyPair keyPair) {
        KEYPAIR = keyPair;
    }

    public static KeyPair getKeyPair() {
        return KEYPAIR;
    }

    public static boolean isLogined() {
        return isLogin;
    }

    public static Registration getUser() {
        return user;
    }

    public static void login(KeyPair keyPair, Registration user) {
        if (keyPair != null && user != null) {
            KEYPAIR = keyPair;
            KeyPairHolder.user = user;
            isLogin = true;
        }
    }

    public static EdDSAPublicKey getPublic() {
        return (EdDSAPublicKey) KEYPAIR.getPublic();
    }

    public static EdDSAPrivateKey getPrivate() {
        return (EdDSAPrivateKey) KEYPAIR.getPrivate();
    }

    public static String base58PublicKey() {
        return KeyPairUtils.encodePublicKeyInBase58((EdDSAPublicKey) KEYPAIR.getPublic());
    }

    public static String hexPrivateKey() {
        return Utils.bytesToHex(KEYPAIR.getPrivate().getEncoded());
    }

    public static String hexPublicKey() {
        return Utils.bytesToHex(KEYPAIR.getPublic().getEncoded());
    }

}
