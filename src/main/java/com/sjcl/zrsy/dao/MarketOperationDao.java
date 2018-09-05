package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.MarketOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MarketOperationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

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
}
