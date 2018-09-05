package com.sjcl.zrsy.service.implement;

import com.sjcl.zrsy.dao.RoleDao;
import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.domain.po.RoleRegistration;
import com.sjcl.zrsy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Repository
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDao roledao;

    @Override
    public boolean registration(RoleRegistration roleRegistration)
    {
        return roledao.insertRegistration(roleRegistration);
    }

    @Override
    public List<String> login(RoleLogin roleLogin)
    {
        return roledao.getLogin(roleLogin);
    }

    @Override
    public String  picturechange(String id,String picture){
        //        //照片生成
        String path="img/"+ id +".jpg";//图片路径
        File file=new File(path);
        String str64= picture;
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
            return path;
        } catch (Exception e) {
           return "照片读取失败";
        }

    }
}
