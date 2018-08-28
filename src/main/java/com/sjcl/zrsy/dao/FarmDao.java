package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.FarmReception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FarmDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //插入养殖场相关操作
    public boolean insertFarmoperation(FarmOperation farmOperation) {
        try {
            jdbcTemplate.update("insert into pig_operation(Id,Operation,Content,Remark,Time) values (?,?,?,?,?)",
                    farmOperation.getId(), farmOperation.getOperation(), farmOperation.getContent(), farmOperation.getRemark(), farmOperation.getTime());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertFarmreception(FarmReception farmReception) {
        try {
            jdbcTemplate.update("insert into  pig_idcard(Id,Farm_id,Breeder_id, Birthday, Breed, Gender,BirthWeight) values  (?, ?, ?, ?, ?,?,?)",
                   farmReception.getId(), farmReception.getFarmId(),farmReception.getBreederId(), farmReception.getDate(), farmReception.getBreed(), farmReception.getGender(), farmReception.getWeight());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
