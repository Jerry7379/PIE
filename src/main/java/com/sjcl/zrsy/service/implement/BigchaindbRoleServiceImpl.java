package com.sjcl.zrsy.service.implement;

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
        keyPairService.save(keyPair, registration.getPassword());
        registration.setPicture(null);

        return super.registration(registration);
    }
}
