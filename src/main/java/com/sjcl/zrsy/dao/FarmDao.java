package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.FarmOperation;
import com.sjcl.zrsy.domain.dto.FarmReception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FarmDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //插入养殖场相关操作
    public boolean insertFarmOperation(FarmOperation farmOperation) {
        try {
            jdbcTemplate.update("insert into farm_operation(id,operation,content,remark,time) values (?,?,?,?,?)",
                    farmOperation.getId(), farmOperation.getOperation(), farmOperation.getContent(), farmOperation.getRemark(), farmOperation.getTime());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertFarmReception(FarmReception farmReception) {
        try {
            jdbcTemplate.update("insert into  traceability_idcard(id,farm_id,breeder_id, birthday, breed, gender,birthweight) values  (?, ?, ?, ?, ?,?,?)",
                   farmReception.getId(), farmReception.getFarmId(),farmReception.getBreederId(), farmReception.getDate(), farmReception.getBreed(), farmReception.getGender(), farmReception.getWeight());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //检查是否重复
    public int getFarmCheck(String id) {
        List<String> checkid=jdbcTemplate.query("select id from traceability_idcard where id='"+id+"'", new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                 return resultSet.getString("id");
            }
        });
       return checkid.size();

    }
}
