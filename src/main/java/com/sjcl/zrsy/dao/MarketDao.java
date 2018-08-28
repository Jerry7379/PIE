package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.MarketReception;
import com.sjcl.zrsy.domain.MarketOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MarketDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public boolean updateMarketReception(MarketReception marketReception){
        try {
            if(jdbcTemplate.update("UPDATE pig_idcard SET Supermarket_id = ? WHERE Id = ?", marketReception.getMarketId(), marketReception.getId())==1)
                return true;
            else
                return false;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean insertMarketOperation(MarketOperation marketOperation){
        try {
            if (jdbcTemplate.update("INSERT INTO pig_operation (Id, Operation, Content, Remark, Time) VALUES (?, ?, ?, ?, ?)", marketOperation.getId(), marketOperation.getOperation(), marketOperation.getContent(), marketOperation.getRemark(), marketOperation.getTime()) == 1)
                return true;
            else
                return false;
        }
        catch (Exception e) {
            return false;
        }
    }
}
