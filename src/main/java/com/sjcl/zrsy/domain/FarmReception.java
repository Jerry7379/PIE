package com.sjcl.zrsy.domain;


import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import java.util.Date;

public class FarmReception {
    //public static final int male=0;
   // public static final int female=1;

//    enum Gender {
//
//    }
    @Length(min = 13,max=13,message = "猪id为13位")
    private String id;
    @Length(min = 6,max=6,message = "养殖场id为6位")
    private String farmId;
    @Length(min=6,max=6,message = "饲养员id")
    private String breederId;
    @Past(message="日期不符合规定")
    private Date date;

    private String breed;//品种


    private String gender;//性别

    @Digits(integer = 99,fraction = 99,message = "出生体重输入错误")
    private Double weight;//出生体重

    public FarmReception(@Length(min = 13, max = 13, message = "猪id为13位") String id, @Length(min = 6, max = 6, message = "养殖场id为6位") String farmId, @Length(min = 6, max = 6, message = "饲养员id") String breederId, @Past(message = "日期不符合规定") Date date, String breed, String gender, @Digits(integer = 99, fraction = 99, message = "出生体重输入错误") Double weight) {
        this.id = id;
        this.farmId = farmId;
        this.breederId = breederId;
        this.date = date;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
    }

    public FarmReception() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getBreederId() {
        return breederId;
    }

    public void setBreederId(String breederId) {
        this.breederId = breederId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
