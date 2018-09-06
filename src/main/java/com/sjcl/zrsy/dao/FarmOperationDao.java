package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.FarmOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FarmOperationDao {
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

}
