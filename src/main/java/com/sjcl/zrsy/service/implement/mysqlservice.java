package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.PigDao;
import com.sjcl.zrsy.dao.RoleDao;
import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.domain.User;
import com.sjcl.zrsy.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class mysqlservice implements IPigService {
    @Autowired
    private PigDao pigDao ;
    @Autowired
    private RoleDao roledao;
    @Override //角色注册，mysql服务
    public String  registration(Registration registration)
    {
         return pigDao.insertRegistration(registration);
    }

    @Override
    public String slaughterreceiver(PigSlaughterReceiver receiver)
    {
        return pigDao.slaughterreceive(receiver);
    }

    @Override
    public String test(User user)
    {
        return roledao.getall(user);
    }


}
