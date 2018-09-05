package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.SlaughterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SlaughterOperationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertSlaughteroperartion(SlaughterOperation slaughterOperation) {
        try {
            int insertSlaughterOperationResult = jdbcTemplate.update("INSERT INTO slaughter_operation (id, operation, content,remark ,time) VALUES (?, ?, ?,?, ?)", slaughterOperation.getId(), slaughterOperation.getOperation(), slaughterOperation.getContent() + "+" + slaughterOperation.getIsAcid(), slaughterOperation.getRemark(), slaughterOperation.getTime());
            return insertSlaughterOperationResult > 0;
        }catch (Exception e)
        {
            return false;
        }
    }
}
