package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.po.Registration;
import com.sjcl.zrsy.domain.dto.RoleLogin;
import com.sjcl.zrsy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@RestController
public class Rolecontroller {
    @Autowired
    IRoleService roleService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.US);


    /**
     * register
     * @param registration
     * @return
     */
    @PostMapping("/registration")
    public String registration(@RequestBody Registration registration) {
        registration.setPicture(roleService.picturechange(registration.getRegistrationId(), registration.getPicture()));
        if (roleService.registration(registration)) {
            return resourceBundle.getString("RegistrationSuccessful");
        } else {
            return resourceBundle.getString("RegistrationFailed");
        }
    }
}
