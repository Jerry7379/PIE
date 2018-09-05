package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.po.Registration;
import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@RestController
public class Rolecontroller {
    @Autowired
    IRoleService roleService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("messages", Locale.US);

    @PostMapping("/registration") //角色注册
    public String registration(@RequestBody Registration registration)
    {
        registration.setPicture(roleService.picturechange(registration.getRegistrationId(), registration.getPicture()));
        if(roleService.registration(registration)){
            //return "注册成功，请等候审核";
            return resourceBundle.getString("RegistrationSuccessful");
        }
        else {
            //return "注册失败";
            return resourceBundle.getString("RegistrationFailed");
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody RoleLogin roleLogin, HttpSession session)  {
        List<String> infos= roleService.login(roleLogin);
        if(infos.size()==0)
       {
           //return "账户不存在，请重输入";
           return resourceBundle.getString("AccountNotExist");
       }
       else {
            String info[]=infos.get(0).split(";");
           if (info[0].equals(roleLogin.getPassword())) {
               session.setAttribute("userInfo", roleLogin.getName());
               session.setAttribute("type", info[1]);
               return  info[1];
           }
          else {
              //return "密码输入错误";
               return resourceBundle.getString("IncorrectPassword");
           }
       }
    }
    @GetMapping("/logout")
    public void logout(HttpSession session){
        session.setAttribute("userInfo",null);
        session.setAttribute("type",null);
    }


}
