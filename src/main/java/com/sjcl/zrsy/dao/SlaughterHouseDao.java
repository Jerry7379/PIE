package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.SlaughterReception;
import com.sjcl.zrsy.domain.SlaughterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SlaughterHouseDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean updateSlaughterreception(SlaughterReception receiver)//屠宰场检疫（还没数据检查）
    {
        try {
            if(jdbcTemplate.update("update traceability_idcard set slaughterhouse_id=?,checker_id=?,ischeck=? where id=?", receiver.getSlaughterId(), receiver.getCheckerId(), receiver.getIsCheck(), receiver.getId())==1)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    public boolean insertSlaughteroperartion(SlaughterOperation slaughterOperation){
        try {
            int a = jdbcTemplate.update("INSERT INTO slaughter_operation (id, operation, content,remark ,time) VALUES (?, ?, ?,?, ?)", slaughterOperation.getId(), slaughterOperation.getOperation(), slaughterOperation.getContent() + "+" + slaughterOperation.getIsAcid(), slaughterOperation.getRemark(), slaughterOperation.getTime());
            int b;
            if(slaughterOperation.getIsAcid()!=null)
            {
                b = jdbcTemplate.update("UPDATE pig_idcard SET Acider_id = ?, Isacid = ? WHERE Id= ?", slaughterOperation.getContent(), slaughterOperation.getIsAcid(), slaughterOperation.getId());
            }
            else
                b=1;

            if(a==1&&b==1)
                return true;
            else
                return false;
        }catch (Exception e)
        {
            return false;
        }
    }
}
