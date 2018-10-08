package com.sjcl.zrsy.dao.implement.bigchaindb;

import com.sjcl.zrsy.domain.po.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationDao implements com.sjcl.zrsy.dao.IRegistrationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Registration getLoginByRegistrationId(String registrationId){

    }

    @Override
    public boolean insertRegistration(Registration registration){


    }


}
