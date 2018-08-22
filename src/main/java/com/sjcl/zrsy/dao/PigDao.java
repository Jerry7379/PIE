package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.PigSlaughterReceiver;
import com.sjcl.zrsy.domain.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;



@Component
public class PigDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public String insertRegistration(Registration registration)//角色注册
    {
        //判断email属性的格式是否符这正确
        String email=registration.getEmail();
        String regex="^[A-Za-z0-9]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";//email正则表达式
        if(email.matches(regex)==false||email.equals("")) {
            return ("email格式：" + email.matches(regex));
        }

        //判断type的值是否符合规定
        String type=registration.getType();
        if(!(type.equals("养殖场")||type.equals("屠宰场")||type.equals("超市")||type.equals("物流")))
        {
            return ("type格式：false");
        }


        //照片生成
        String path="img/"+registration.getRegistrationId()+".jpeg";//图片路径
        File file=new File(path);
        String str64=registration.getPicture();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(str64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(file);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            return "照片读取失败";
        }
        registration.setPicture(path);

        //判断registrationId格式
        String registrationId=registration.getRegistrationId();
        regex="^[0-9]{1,40}$";//registration正则表达式
        if(!registrationId.matches(regex)&&registrationId.length()>6) {
            //map.put("id","false");
            return "注册号格式有误";
        }

        //判断name格式
        if(registration.getName().length()>15) {
            //map.put("name","false");
            return "名称长度有问题";
        }

        //判断location的格式
        if(registration.getLocation().length()>20) {
            //map.put("location","false");
            return "地址长度有问题";
        }

        //判断legalrep的格式
        if(registration.getLegalrep().length()>5) {
            //map.put("legalrep","false");
            return "法人代表长度有问题";
        }

        //判断capital的格式
        if(registration.getCapital().length()>10) {
            //map.put("capital","false");
            return "资金有问题";
        }
        //数据格式判断完成，进行数据库操作
        try {
            jdbcTemplate.update("insert into registration (Registration_id,Types,Email,Picture,Name,Location,Legal_rep,Capital,DEstablishment) value(?,?,?,?,?,?,?,?,?)",
                    registration.getRegistrationId(), registration.getType(), registration.getEmail(), registration.getPicture(), registration.getName(), registration.getLocation(), registration.getLegalrep(), registration.getCapital(), registration.getDestablishment());
            return "{注册成功，请等待审核，审核通过会以邮件的形式告知，请注意查收}";
        }catch(Exception e) {
            return "未知错误";
        }


    }

    public String slaughterreceive(PigSlaughterReceiver receiver)//屠宰场检疫（还没数据检查）
    {
            try {
                jdbcTemplate.update("update pig_idcard set Slaughterhouse_id=?,Checker_id=?,Ischeck=? where Id=?", receiver.getSlaughterid(), receiver.getCheckerid(), receiver.getIscheck(), receiver.getPigid());
                return "更新成功";
            }catch (Exception e){
                return e.toString();
            }



    }
}
