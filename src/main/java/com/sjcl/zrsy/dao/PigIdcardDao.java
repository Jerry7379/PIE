package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.po.SlaughterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PigIdcardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean updatePigIdcard(SlaughterOperation slaughterOperation){
        int updatePigIdCardResult = jdbcTemplate.update("UPDATE pig_idcard SET Acider_id = ?, Isacid = ? WHERE Id= ?", slaughterOperation.getContent(), slaughterOperation.getIsAcid(), slaughterOperation.getId());
        return updatePigIdCardResult > 0;
    }
}
