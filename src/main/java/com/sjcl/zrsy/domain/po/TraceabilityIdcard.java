package com.sjcl.zrsy.domain.po;

import java.util.Date;

public class TraceabilityIdcard {
    // 小猪标签
    private String id;

    // 出生日期
    private Date birthday;

    // 猪品种
    private String breed;

    // 性别
    private String gender;

    // 是否健康
    private int ishealth;

    // 是否排酸
    private int isacid;

    // 排酸人员id
    private String aciderId;

    // 检疫是否合格
    private int ischeck;

    // 检疫人员id
    private String checkerId;

    // 养殖场id
    private String farmId;

    // 物流公司id
    private String logisticsId;

    // 屠宰场id
    private String slaughterhouseId;

    // 超市id
    private String supermarketId;

    // 饲养员id
    private String breederId;

    // 车牌号
    private String carId;

    // 司机id
    private String driverId;

    // 出生体重
    private double birthweight;


    public TraceabilityIdcard() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public int getIshealth() {
        return ishealth;
    }

    public void setIshealth(int ishealth) {
        this.ishealth = ishealth;
    }

    public int getIsacid() {
        return isacid;
    }

    public void setIsacid(int isacid) {
        this.isacid = isacid;
    }

    public String getAciderId() {
        return aciderId;
    }

    public void setAciderId(String aciderId) {
        this.aciderId = aciderId;
    }

    public int getIscheck() {
        return ischeck;
    }

    public void setIscheck(int ischeck) {
        this.ischeck = ischeck;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getSlaughterhouseId() {
        return slaughterhouseId;
    }

    public void setSlaughterhouseId(String slaughterhouseId) {
        this.slaughterhouseId = slaughterhouseId;
    }

    public String getSupermarketId() {
        return supermarketId;
    }

    public void setSupermarketId(String supermarketId) {
        this.supermarketId = supermarketId;
    }

    public String getBreederId() {
        return breederId;
    }

    public void setBreederId(String breederId) {
        this.breederId = breederId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public double getBirthweight() {
        return birthweight;
    }

    public void setBirthweight(double birthweight) {
        this.birthweight = birthweight;
    }
}
