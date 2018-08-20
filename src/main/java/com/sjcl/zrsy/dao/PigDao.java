package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.Pig_Birth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PigDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String insertBirth(Pig_Birth pigBirth) {

        //判断小猪id格式
        String pigId = pigBirth.getId();
         String regex = "^[0-9]{13}$";//id正则表达式
        if (!pigId.matches(regex)) {
            return "小猪id格式有误";
        }

        //判断养殖场id格式
        String farmId = pigBirth.getFarm_id();
        regex = "^[0-9]{6}$";//farm id正则表达式
        if (!farmId.matches(regex)) {
            return "养殖场id格式有误";
        }


        //判断breed的值是否符合规定
        String breedType = pigBirth.getBreed();
        if (!(breedType.equals("品种1") || breedType.equals("品种2") || breedType.equals("品种3") || breedType.equals("品种4"))) {
            return ("breedType格式：false");
        }

        //判断breed的值是否符合规定
        String genderType = pigBirth.getGender();
        if (!(genderType.equals("雌") || genderType.equals("雄"))) {
            return ("genderType格式：false");
        }

        //判断小猪出生体重格式是否符合正确
        String birthWeight=pigBirth.getWeight();
        regex="^[0-9]{1,2}+(.[0-9]{2})?$";//小猪出生体重正则表达式
        if(!birthWeight.matches(regex)||birthWeight.equals("")) {
            return ("birthWeight格式：" + birthWeight.matches(regex));
        }

        try {
            jdbcTemplate.update("insert into  pig_idcard(Id,Farm_id, Birthday, Breed, Gender,BirthWeight) values  (?, ?, ?, ?, ?,?)",
                    pigBirth.getId(), pigBirth.getFarm_id(), pigBirth.getDate(), pigBirth.getBreed(), pigBirth.getGender(), pigBirth.getWeight());
            return "填写成功";
        }catch (Exception e){
            return "未知错误";
        }
    }





    public  String insertFPO(FarmOperation farmOperation){

        //判断小猪id格式
        String pigId = farmOperation.getId();
        String regex = "^[0-9]{13}$";//id正则表达式
        if (!pigId.matches(regex)) {
            return "小猪id格式有误";
        }

        //判断breed的值是否符合规定
        String operationType = farmOperation.getOperation();
        if (!(operationType.equals("喂食") || operationType.equals("健康") || operationType.equals("防疫") || operationType.equals("出栏体重"))) {
            return ("operationType格式：false");
        }

        try {
            jdbcTemplate.update("insert into pig_operation(Id,Operation,Content,Remark,Time) values (?,?,?,?,?)",
                    farmOperation.getId(), farmOperation.getOperation(), farmOperation.getContent(), farmOperation.getRemark(), farmOperation.getTime());
            return "填写成功！";
        }catch(Exception e) {
            return "未知错误";
        }
    }



}
