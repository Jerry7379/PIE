package com.sjcl.zrsy.bigchaindb;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.util.KeyPairUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.sjcl.zrsy.domain.dto.BigchaindbData;
import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.domain.po.Operation;
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
        BigchaindbData data = new BigchaindbData(login);

        String id = BigchaindbUtil.createAsset(data);

        System.out.println(id);
        Assert.assertNotNull(id);
    }

    @Test
    public void testGetAsset() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        RoleLogin onlyId = (RoleLogin) BigchaindbUtil.getAsset(assetId);

        Assert.assertEquals("zanghongfei", onlyId.getName());
        Assert.assertEquals("zanghongfei", onlyId.getPassword());

        RoleLogin byType = BigchaindbUtil.getAsset(assetId, RoleLogin.class);

        Assert.assertEquals("zanghongfei", byType.getName());
        Assert.assertEquals("zanghongfei", byType.getPassword());
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
        String id = BigchaindbUtil.getLastTransactionId(assetId);

        Assert.assertNotNull(id);
    }

    @Test
    public void testBigchaindbDataToBean() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LinkedTreeMap op = new LinkedTreeMap();
        op.put("content", "testBigchaindbDataToBean-content");
        op.put("operation", "testBigchaindbDataToBean-operation");
        op.put("id", "testBigchaindbDataToBean-id");
        op.put("remark", "testBigchaindbDataToBean-remark");
        op.put("time", "testBigchaindbDataToBean-time");

        LinkedTreeMap<String, Object> data = new LinkedTreeMap<>();
        data.put("type", Operation.class.getCanonicalName());
        data.put("data", op);


        Object bean = BigchaindbUtil.bigchaindbDataToBean(data);
        Assert.assertTrue(bean instanceof Operation);
        Operation opObj = (Operation) bean;
        Assert.assertEquals("testBigchaindbDataToBean-content", opObj.getContent());
    }
}
