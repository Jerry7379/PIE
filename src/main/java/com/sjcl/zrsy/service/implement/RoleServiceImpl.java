package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.RoleDao;
import com.sjcl.zrsy.domain.*;
import com.sjcl.zrsy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDao roledao;

    @Override
    public boolean registration(RoleRegistration roleRegistration)
    {
        return roledao.insertRegistration(roleRegistration);
    }

    @Override
    public List<String> login(RoleLogin roleLogin)
    {
        return roledao.getLogin(roleLogin);
    }

    @Override
    public String  picturechange(String id,String picture){
        return roledao.picturechange(id,picture);
    }
}
