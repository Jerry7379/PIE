package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import com.sjcl.zrsy.domain.po.Registration;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
public class BigchaindbRoleServiceImpl extends RoleServiceImpl {
    @Autowired
    private KeyPairService keyPairService;

    @Override
    public boolean registration(Registration registration) {
        KeyPairGenerator keyPairGenerator = new KeyPairGenerator();
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        KeyPairHolder.setKeyPair(keyPair);
        String password = registration.getPassword();
        registration.setPassword(null);
        //registration.setPublicKey((EdDSAPublicKey) keyPair.getPublic());//TODO:公钥放不进去，链上显示为null，一旦在此处获取，报错，未找到原因
        boolean ret = super.registration(registration);
        if (ret) {
            ret = keyPairService.save(KeyPairHolder.getKeyPair(), password);
        }
        return ret;
    }
}
