package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.PigDao;
import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.domain.User;
import com.sjcl.zrsy.service.IPigService;
import com.sjcl.zrsy.domain.Pig_Birth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service

public class mysqlservice implements IPigService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private PigDao pigDao ;
    @Override //角色注册，mysql服务
    public String  registration(Registration registration)
    {
         return pigDao.insertRegistration(registration);
    }


}
