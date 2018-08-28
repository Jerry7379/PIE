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
            if(jdbcTemplate.update("update pig_idcard set Slaughterhouse_id=?,Checker_id=?,Ischeck=? where Id=?", receiver.getSlaughterId(), receiver.getCheckerId(), receiver.getIsCheck(), receiver.getId())==1)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    public boolean insertSlaughteroperartion(SlaughterOperation slaughterAcid){
        try {
            int a = jdbcTemplate.update("INSERT INTO pig_operation (Id, Operation, Content,Remark ,Time) VALUES (?, ?, ?,?, ?)", slaughterAcid.getId(), slaughterAcid.getOperation(), slaughterAcid.getContent() + "+" + slaughterAcid.getIsAcid(), slaughterAcid.getRemark(), slaughterAcid.getTime());
            int b;
            if(slaughterAcid.getIsAcid()!=null)
            {
                b = jdbcTemplate.update("UPDATE pig_idcard SET Acider_id = ?, Isacid = ? WHERE Id= ?", slaughterAcid.getContent(), slaughterAcid.getIsAcid(), slaughterAcid.getId());
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
