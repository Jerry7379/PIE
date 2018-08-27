package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.SlaughterAcid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SlaughterHouseDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String slaughterreception(PigSlaughterReceiver receiver)//屠宰场检疫（还没数据检查）
    {
        try {
            jdbcTemplate.update("update pig_idcard set Slaughterhouse_id=?,Checker_id=?,Ischeck=? where Id=?", receiver.getSlaughterid(), receiver.getCheckerid(), receiver.getIscheck(), receiver.getPigid());
            return "更新成功";
        }catch (Exception e){
            return e.toString();
        }
    }

    public String slaughteroperartion(SlaughterAcid slaughterAcid){
        jdbcTemplate.update("INSERT INTO pig_operation (Id, Operation, Content,Remark ,Time) VALUES (?, ?, ?,?, ?)",slaughterAcid.getPigId(), slaughterAcid.getPigOperation(),slaughterAcid.getPigPspid()+"+"+slaughterAcid.getPigIsacid(), slaughterAcid.getPigRemark(), slaughterAcid.getPigTime());
        jdbcTemplate.update("UPDATE pig_idcard SET Acider_id = ?, Isacid = ? WHERE Id= ?", slaughterAcid.getPigPspid(), slaughterAcid.getPigIsacid(), slaughterAcid.getPigId());
        return "(排酸情况添加成功)";
    }
}
