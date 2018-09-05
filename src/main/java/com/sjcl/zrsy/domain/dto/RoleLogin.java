package com.sjcl.zrsy.domain.dto;

import org.hibernate.validator.constraints.Length;

public class RoleLogin {
    //注册id
    @Length(min = 6,max = 6,message="注册id的长度为6")
    private String name;
    //密码
    @Length(min = 6,max = 16,message = "密码长度为6-16")
    private String password;

    public RoleLogin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
