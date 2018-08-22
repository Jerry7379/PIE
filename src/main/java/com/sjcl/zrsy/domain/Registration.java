package com.sjcl.zrsy.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Registration {    //注册模块
    private String registrationId; //注册ID 6位
    private String type;//类型 5位
    private String email;//邮箱 30位
    private String picture;//营业执照照片地址；255位
    private String name;//公司名称 15位
    private String location;//公司地址 20位
    @JsonProperty(value="legalRep")
    private String legalrep;//法人代表 5位
    private String capital;//注册资本 10位
    @JsonProperty(value="destablishment")
    private String destablishment;//成立日期 date
    private String account;//账号 255
    private String password;//密码 255
    private String key;//钱包文件

    public Registration(){

    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLegalrep() {
        return legalrep;
    }

    public void setLegalrep(String legalrep) {
        this.legalrep = legalrep;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getDestablishment() {
        return destablishment;
    }

    public void setDestablishment(String destablishment) {
        this.destablishment = destablishment;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
