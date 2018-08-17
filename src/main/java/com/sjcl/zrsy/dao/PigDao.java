package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.Pig_Birth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PigDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertBirth(Pig_Birth pigBirth) {
        jdbcTemplate.update("insert into  pig_idcard(Id,Farm_id, Birthday, Breed, Gender,BirthWeight) values  (?, ?, ?, ?, ?,?)", pigBirth.getId(), pigBirth.getFarm_id(), pigBirth.getDate(), pigBirth.getBreed(), pigBirth.getGender(),pigBirth.getWeight());
    }

    public  void insertFPO(FarmOperation farmOperation){

        jdbcTemplate.update("insert into pig_operation(Id,Operation,Content,Remark,Time) values (?,?,?,?,?)",farmOperation.getId(),farmOperation.getOperation(),farmOperation.getContent(),farmOperation.getRemark(),farmOperation.getTime());
    }

}
