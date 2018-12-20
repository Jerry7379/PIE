package com.sjcl.zrsy.domain.dto;


import com.sjcl.zrsy.bigchaindb.KeyPairHolder;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import java.sql.Date;


public class FarmReception {
    @Length(min = 13, max = 13, message = "猪id为13位")
    private String id;

    @Length(min = 6, max = 6, message = "饲养员id")
    private String breederId;

    @Past(message = "日期不符合规定")
    private Date date;

    private String breed;//品种

    private String gender;//性别

    @Digits(integer = 99, fraction = 99, message = "出生体重输入错误")
    private int weight;//出生体重

    public FarmReception() {
    }

    public TraceabilityIdcard toFarm() {
        TraceabilityIdcard farm = new TraceabilityIdcard();
        farm.setId(this.id);
        farm.setFarmId(KeyPairHolder.getUser().getRegistrationId());
        farm.setBreederId(this.breederId);
        farm.setBreed(this.breed);
        farm.setBirthday(this.date);
        farm.setGender(this.gender);
        farm.setBirthweight(this.weight);
        return farm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
