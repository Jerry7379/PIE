package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.RoleRegistration;
import com.sjcl.zrsy.domain.RoleLogin;
import com.sjcl.zrsy.service.implement.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class Rolecontroller {

    @Autowired
    IRoleService roleService;
    @PostMapping("/registration") //角色注册
    public String registration(@RequestBody RoleRegistration test)
    {
        test.setPicture(roleService.picturechange(test.getRegistrationId(),test.getPicture()));
        if(roleService.registration(test)){
            return "注册成功，请等候审核";
        }
        else
            return "注册失败";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody RoleLogin roleLogin, HttpSession session)  {
        List<String> infos= roleService.login(roleLogin);
        String info[]=infos.get(0).split(";");
        if(!info[0].equals(roleLogin.getPassword()))
       {
           return "此账户不存在，请重输入";

       }
       else {
           if (info[0].equals(roleLogin.getPassword())) {
               session.setAttribute("userInfo", roleLogin.getName());
               session.setAttribute("type", info[1]);
               return  info[1];
           }
          else
               return "密码输入错误";
       }
    }
}
