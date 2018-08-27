package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Repository
public class PigDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


//减少代码的重写******
    public String insertMAC(SlaughterAcid slaughterAcid){
        jdbcTemplate.update("INSERT INTO pig_operation (Id, Operation, Content,Remark ,Time) VALUES (?, ?, ?,?, ?)",slaughterAcid.getPigId(), slaughterAcid.getPigOperation(),slaughterAcid.getPigPspid()+"+"+slaughterAcid.getPigIsacid(), slaughterAcid.getPigRemark(), slaughterAcid.getPigTime());
        jdbcTemplate.update("UPDATE pig_idcard SET Acider_id = ?, Isacid = ? WHERE Id= ?", slaughterAcid.getPigPspid(), slaughterAcid.getPigIsacid(), slaughterAcid.getPigId());
        return "(排酸情况添加成功)";
    }








}
