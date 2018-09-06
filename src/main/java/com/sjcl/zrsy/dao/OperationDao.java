package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.FarmOperation;
import com.sjcl.zrsy.domain.dto.MarketOperation;
import com.sjcl.zrsy.domain.dto.SlaughterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OperationDao {
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

    public boolean insertMarketOperation(MarketOperation marketOperation){
        try {
            if (jdbcTemplate.update("INSERT INTO market_operation (id, operation, content, remark, time) VALUES (?, ?, ?, ?, ?)", marketOperation.getId(), marketOperation.getOperation(), marketOperation.getContent(), marketOperation.getRemark(), marketOperation.getTime()) == 1)
                return true;
            else
                return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean insertSlaughteroperartion(SlaughterOperation slaughterOperation) {
        try {
            int insertSlaughterOperationResult = jdbcTemplate.update(
                    "INSERT INTO slaughter_operation (id, operation, content,remark ,time) VALUES (?, ?, ?,?, ?)",
                    slaughterOperation.getId(),
                    slaughterOperation.getOperation(),
                    slaughterOperation.getContent() + "+" + slaughterOperation.getIsAcid(),
                    slaughterOperation.getRemark(),
                    slaughterOperation.getTime()
            );
            return insertSlaughterOperationResult > 0;
        }catch (Exception e)
        {
            return false;
        }
    }
}
