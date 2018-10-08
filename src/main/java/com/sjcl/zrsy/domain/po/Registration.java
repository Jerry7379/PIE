package com.sjcl.zrsy.domain.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import sun.misc.BASE64Decoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class Registration {    //注册模块
    @Length(min = 6,max=6,message = "注册号为6位")
    private String registrationId;

    private String type;//类型 5位

    @Email(message = "邮箱地址格式有问题")
    private String email;//邮箱 30位

    private String picture;//营业执照照片地址；255位

    @Length(min = 15,max = 15,message = "公司名称长度为15")
    private String name;//公司名称 15位

    @Length(min = 20,max = 20,message = "公司地址长度为20")
    private String location;//公司地址 20位

    @JsonProperty(value="legalRep")
    @Length(min = 5,max = 5,message = "法人代表名称长度为15")
    private String legalrep;//法人代表 5位

    @Min(value = 0,message = "注册资本必须为大于0的整数")
    private String capital;//注册资本 10位

    @JsonProperty(value="destablishment")
    @Past(message = "成立日期必须为过去的日期")
    private Date destablishment;//成立日期 date

    private String account;//账号 255
    private String password;//密码 255

    public Registration()
    {

    }

    public void pictureChange() throws IOException {
        //        //照片生成
        String path="img/"+ registrationId +".jpg";//图片路径
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
            this.setPicture(path);
        } catch (Exception e) {
            throw e;
        }

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

    public String getLegal_rep() {
        return legalrep;
    }

    public void setLegal_rep(String legal_rep) {
        this.legalrep = legal_rep;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Date getDestablishment() {
        return destablishment;
    }

    public void setDestablishment(Date destablishment) {
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
}
