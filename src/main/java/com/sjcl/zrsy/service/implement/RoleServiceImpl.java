package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.IRegistrationDao;
import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.domain.po.Registration;
import com.sjcl.zrsy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRegistrationDao roledao;

    @Override
    public boolean registration(Registration registration)
    {
        try {
            registration.pictureChange();
        } catch (IOException e) {
            return false;
        }
        return roledao.insertRegistration(registration);
    }

    @Override
    public Registration login(RoleLogin roleLogin)
    {
        return roledao.getLoginByRegistrationId(roleLogin.getName());
    }
}
