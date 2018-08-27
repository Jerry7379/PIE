package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.MarketREC;
import com.sjcl.zrsy.domain.MarketWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MarketDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public String marketReception(MarketREC marketRec){
        String idA = marketRec.getIdArray();
        String date[]=idA.split(";");
        for (int i = 0; i < date.length; i++) {
            System.out.println(date[i]);
            jdbcTemplate.update("UPDATE pig_idcard SET Supermarket_id = ? WHERE Id = ?", marketRec.getMarketId(), date[i]);
        }
        return "{绑定成功}";
    }

    public String marketOperation(MarketWork marketWork){
        jdbcTemplate.update("INSERT INTO pig_operation (Id, Operation, Content, Remark, Time) VALUES (?, ?, ?, ?, ?)", marketWork.getPigId(), marketWork.getPigOperation(), marketWork.getPigContent(), marketWork.getPigRemark(), marketWork.getPigTime());
        return "{添加操作成功}";
    }
}
