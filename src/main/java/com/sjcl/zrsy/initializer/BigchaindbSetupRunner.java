package com.sjcl.zrsy.initializer;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import net.i2p.crypto.eddsa.EdDSASecurityProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.Security;

@Component
public class BigchaindbSetupRunner implements CommandLineRunner {

    private static final String BLOCKCHAINDB_APPID_KEY = "app_id";
    private static final String BLOCKCHAINDB_APPKEY_KEY = "app_key";

    @Value("${blockchaindb.base-url}")
    private String baseUrl;
    @Value("${blockchaindb.appid}")
    private String appId;
    @Value("${blockchaindb.appkey}")
    private String appKey;

    @Override
    public void run(String... args) throws Exception {
        Security.addProvider(new EdDSASecurityProvider());

        BigchainDbConfigBuilder
                .baseUrl(baseUrl)
                //.addToken(BLOCKCHAINDB_APPID_KEY, appId)
                //.addToken(BLOCKCHAINDB_APPKEY_KEY, appKey)
                .setup();
    }
}
