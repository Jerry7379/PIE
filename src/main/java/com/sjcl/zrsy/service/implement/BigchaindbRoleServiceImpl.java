package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.bigchaindb.KeyPairDao;
import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.domain.po.Registration;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
public class BigchaindbRoleServiceImpl extends RoleServiceImpl {
    @Autowired
    private KeyPairDao keyPairDao;

    @Override
    public boolean registration(Registration registration) {
        KeyPairGenerator keyPairGenerator = new KeyPairGenerator();
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        keyPairDao.save(keyPair, registration.getPassword());
        registration.setPicture(null);

        return super.registration(registration);
    }

    @Override
    public Registration login(RoleLogin roleLogin) {
        return super.login(roleLogin);
    }
}
