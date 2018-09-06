package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.SlaughterOperation;
import com.sjcl.zrsy.domain.po.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OperationDao {
    private static final String TABLE_NAME_FARM_OPERATION = "farm_operation";
    private static final String TABLE_NAME_SLAUGHTER_OPERATION = "slaughter_operation";
    private static final String TABLE_NAME_MARKET_OPERATION = "market_operation";



    @Autowired
    JdbcTemplate jdbcTemplate;


    //插入养殖场相关操作
    public boolean insertFarmOperation(Operation operation) {
        try {
            return insertOperation(TABLE_NAME_FARM_OPERATION, operation);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertMarketOperation(Operation operation){
        try {
            return insertOperation(TABLE_NAME_MARKET_OPERATION, operation);
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

    private boolean insertOperation(String tableName, Operation operation) {
        try {
            int insertSlaughterOperationResult = jdbcTemplate.update(
                    "INSERT INTO " + tableName + " (id, operation, content,remark ,time) VALUES (?, ?, ?, ?, ?)",
                    operation.getId(),
                    operation.getOperation(),
                    operation.getContent(),
                    operation.getRemark(),
                    operation.getTime()
            );
            return insertSlaughterOperationResult > 0;
        }catch (Exception e)
        {
            return false;
        }
    }
}
