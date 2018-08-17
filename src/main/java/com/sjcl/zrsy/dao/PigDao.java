package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.Registration;
import com.sjcl.zrsy.domain.User;
import com.sjcl.zrsy.domain.Zuoye;
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
    public void insertBirth(Zuoye zuoye){

    }
    public void  insertRegistration(Registration registration)
    {
        String path="img/"+registration.getRegistrationId()+".jpeg";
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
            System.out.print("成功读取图片");
        } catch (Exception e) {
            System.out.print ("错误");
        }
        registration.setPicture(path);
        int i=jdbcTemplate.update("insert into registration (Registration_id,Types,Email,Picture,Name,Location,Legal_rep,Capital,DEstablishment) value(?,?,?,?,?,?,?,?,?)",
                registration.getRegistrationId(),registration.getType(),registration.getEmail(),registration.getPicture(),registration.getName(),registration.getLocation(),registration.getLegal_rep(),registration.getCapital(),registration.getDestablishment());
        if(i>0)
        {
            System.out.print("成功插入");
        }
        else{
            System.out.print ("错误");
        }
    }
}
