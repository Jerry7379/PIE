package com.sjcl.zrsy.bigchaindb;

public class KeyPairHolder {
    public static java.security.KeyPair KEYPAIR;

    public static void setKeyPair(java.security.KeyPair keyPair) {
        KEYPAIR = keyPair;
    }

    public static java.security.KeyPair getKeyPair() {
        return KEYPAIR;
    }

}
