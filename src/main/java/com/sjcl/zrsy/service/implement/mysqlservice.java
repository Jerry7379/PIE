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

    @Override
    public void insertpig(Pig_Birth newpig)
    {
        jdbcTemplate.update("insert into pig_birth (Id,Birthday,Breed,Gender,Weight) values  (?,?,?,?,?)", newpig.getId(),newpig.getBirthday(),newpig.getBreed(),newpig.getGender(),newpig.getWeight());
    }
    @Autowired
    private PigDao pigDao ;
    @Override
    public void  registration(Registration registration)
    {
         pigDao.insertRegistration(registration);
    }


}
