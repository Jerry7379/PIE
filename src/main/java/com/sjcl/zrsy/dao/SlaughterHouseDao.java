package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.SlaughterReception;
import com.sjcl.zrsy.domain.po.SlaughterOperation;
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
            int insertSlaughterOperationResult = jdbcTemplate.update("INSERT INTO slaughter_operation (id, operation, content,remark ,time) VALUES (?, ?, ?,?, ?)", slaughterOperation.getId(), slaughterOperation.getOperation(), slaughterOperation.getContent() + "+" + slaughterOperation.getIsAcid(), slaughterOperation.getRemark(), slaughterOperation.getTime());
            if (insertSlaughterOperationResult < 1) {
                return false;
            }
            if (slaughterOperation.getIsAcid() != null) {
                int updatePigIdCardResult = jdbcTemplate.update("UPDATE pig_idcard SET Acider_id = ?, Isacid = ? WHERE Id= ?", slaughterOperation.getContent(), slaughterOperation.getIsAcid(), slaughterOperation.getId());
                return updatePigIdCardResult >= 1;
            }
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
