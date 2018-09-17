package com.sjcl.zrsy.controller;

import com.sjcl.zrsy.domain.po.Registration;
import com.sjcl.zrsy.service.IRoleService;
import com.sjcl.zrsy.tendermint.ActionClass;
import com.sjcl.zrsy.tendermint.ActionMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.ResourceBundle;

@ActionClass
public class RoleAction {
    @Autowired
    IRoleService roleService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.US);


    /**
     * register
     * @param registration
     * @return
     */
    @ActionMethod("registration")
    public String registration(Registration registration) {
        registration.setPicture(roleService.picturechange(registration.getRegistrationId(), registration.getPicture()));
        if (roleService.registration(registration)) {
            return resourceBundle.getString("RegistrationSuccessful");
        } else {
            return resourceBundle.getString("RegistrationFailed");
        }
    }
}
