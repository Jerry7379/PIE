package com.sjcl.zrsy.bigchaindb;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.util.KeyPairUtils;
import com.sjcl.zrsy.domain.dto.AssetData;
import com.sjcl.zrsy.domain.dto.RoleLogin;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.KeyPair;

public class BigchaindbUtilTest {
    private static String privateKey = "MC4CAQAwBQYDK2VwBCIEIJGujw3DMkwqJmdTW9swNlFMWaerSUANgrI1yNy3yWjx";

    private static final String assetId = "783b15513bac10ff14914961fa81fb4a82aabdbd81c30174a445a36caf5b20fb";

    @BeforeClass
    public static void beforeClass() {
        BigchainDbConfigBuilder
                .baseUrl("https://test.bigchaindb.com")
                .addToken("app_id", "80c8cfc2")
                .addToken("app_key", "ae92d3d93e4019bbfc10cc458e7c760b").setup();

        KeyPair keyPair = KeyPairUtils.decodeKeyPair(privateKey);
        KeyPairHolder.setKeyPair(keyPair);
    }

    @Test
    public void testCreateAsset() throws Exception {
        RoleLogin login = new RoleLogin();
        login.setName("zanghongfei");
        login.setPassword("zanghongfei");
        AssetData data = new AssetData(login);

        String id = BigchaindbUtil.createAsset(data);

        System.out.println(id);
        Assert.assertNotNull(id);
    }

    @Test
    public void testGetAsset() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        AssetData<RoleLogin> asset = BigchaindbUtil.getAsset(assetId);

        RoleLogin login = asset.getData();
        Assert.assertEquals("zanghongfei", login.getName());
        Assert.assertEquals("zanghongfei", login.getPassword());
    }

    @Test
    public void testGetCreateTransaction() throws IOException {
        Transaction zanghongfe = BigchaindbUtil.getCreateTransaction(assetId);
        Assert.assertNotNull(zanghongfe);

        Transaction none = BigchaindbUtil.getCreateTransaction("xxxx");
        Assert.assertNull(none);
    }

    @Test
    public void testGetLasetTransactionId() throws IOException {
        String id = BigchaindbUtil.getLastTransactonId(assetId);

        Assert.assertNotNull(id);
    }
}
