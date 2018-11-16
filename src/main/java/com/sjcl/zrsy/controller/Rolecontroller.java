package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.bigchaindb.KeyPairService;
import com.sjcl.zrsy.domain.po.Registration;
import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@RestController
public class Rolecontroller {
    @Autowired
    IRoleService roleService;

    @Autowired
    KeyPairService keyPairService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.US);


    /**
     * register
     * @param registration
     * @return
     */
    @PostMapping("/registration")
    public String registration(@RequestBody Registration registration) {

        Registration user = roleService.login(registration.getRegistrationId());

        if (user != null || keyPairService.isExist(registration.getType(),registration.getRegistrationId())) {
            return resourceBundle.getString("AccountAreadyExist");
        }

        if (roleService.registration(registration)) {
            return resourceBundle.getString("RegistrationSuccessful");
        } else {
            return resourceBundle.getString("RegistrationFailed");
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody RoleLogin roleLogin, HttpSession session) {
        Registration user = roleService.login(roleLogin.getName());
        if (user == null) {
            return resourceBundle.getString("AccountNotExist");
        } else {
            String password = roleLogin.getPassword();
            KeyPair keyPair = keyPairService.get(password,user.getType(),user.getRegistrationId());
            if (keyPair != null) {
                KeyPairHolder.login(keyPair, user);
                session.setAttribute("type", user.getType());
                session.setAttribute("userInfo", user);
                return user.getType();
            }

            return resourceBundle.getString("IncorrectPassword");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
        KeyPairHolder.setKeyPair(null);
    }


}
