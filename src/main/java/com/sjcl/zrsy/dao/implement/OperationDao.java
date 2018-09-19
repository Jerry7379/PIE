package com.sjcl.zrsy.dao.implement;

import com.sjcl.zrsy.domain.po.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationDao {
    private static final String TABLE_NAME_FARM_OPERATION = "farm_operation";
    private static final String TABLE_NAME_SLAUGHTER_OPERATION = "slaughter_operation";
    private static final String TABLE_NAME_MARKET_OPERATION = "market_operation";

    private static final RowMapper<Operation> OPERATION_ROW_MAPPERR = new RowMapper<Operation>() {
        @Override
        public Operation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Operation operation = new Operation();
            operation.setOperation(resultSet.getString("operation"));
            operation.setContent(resultSet.getString("content"));
            operation.setRemark(resultSet.getString("remark"));
            operation.setTime(resultSet.getString("time"));
            return operation;
        }
    };

    @Autowired
    JdbcTemplate jdbcTemplate;


    public boolean insertFarmOperation(Operation operation) {
        try {
            return insertOperation(TABLE_NAME_FARM_OPERATION, operation);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertMarketOperation(Operation operation) {
        try {
            return insertOperation(TABLE_NAME_MARKET_OPERATION, operation);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertSlaughteroperartion(Operation operation) {
        try {
            return insertOperation(TABLE_NAME_SLAUGHTER_OPERATION, operation);
        } catch (Exception e) {
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
        } catch (Exception e) {
            return false;
        }
    }

    public List<Operation> findFarmOperationByPigid(String pigId) {
        return findOperation(TABLE_NAME_FARM_OPERATION, pigId);
    }

    public List<Operation> findMarketOperationByPigid(String pigId) {
        return findOperation(TABLE_NAME_MARKET_OPERATION, pigId);
    }

    public List<Operation> findSlaughterOperationByPigid(String pigId) {
        return findOperation(TABLE_NAME_SLAUGHTER_OPERATION, pigId);
    }

    public List<Operation> findallOperationByPigid(String pigId) {
        List<Operation> operations = new ArrayList<>();
        operations.addAll(findFarmOperationByPigid(pigId));
        operations.addAll(findMarketOperationByPigid(pigId));
        operations.addAll(findSlaughterOperationByPigid(pigId));
        return operations;
    }

    private List<Operation> findOperation(String tableName, String pigId) {
        return jdbcTemplate.query("SELECT operation, content, remark, time FROM " + tableName + " WHERE Id = ?", new Object[]{pigId}, OPERATION_ROW_MAPPERR);
    }
}
