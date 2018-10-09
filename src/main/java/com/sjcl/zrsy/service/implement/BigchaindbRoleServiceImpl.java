package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import com.sjcl.zrsy.domain.po.Registration;
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
        boolean superRet = super.registration(registration);
        boolean saveRet = keyPairService.save(KeyPairHolder.getKeyPair(), password);
        return superRet && saveRet;
    }
}
