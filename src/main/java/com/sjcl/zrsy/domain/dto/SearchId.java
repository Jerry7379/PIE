package com.sjcl.zrsy.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchId {
    private String id;
    private String birthday;
    private String breed;
    private String gender;
    private String isacid;
    private String ischeck;

    private String farmLocation;
    private String farmId;
    private String breederId;
    private String farmName;

    private String slaughterhouseId;
    private String slaughterhouseName;
    private String slaughterhouseLocation;
    private String checkerId;
    private String aciderId;

    private String logisticsId;
    private String logisticsName;
    private String logisticsLocation;

    private String supermarketId;
    private String supermarketName;
    private String supermarketLocation;
    private String salespersonId;

    private List<SearchOperation> operations = new ArrayList<>();

    public List<SearchOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<SearchOperation> operations) {
        this.operations = operations;
    }

    public SearchId(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public String getIsacid() {
        return isacid;
    }

    public void setIsacid(String isacid) {
        this.isacid = isacid;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = farmLocation;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getBreederId() {
        return breederId;
    }

    public void setBreederId(String breederId) {
        this.breederId = breederId;
    }

    public String getSlaughterhouseId() {
        return slaughterhouseId;
    }

    public void setSlaughterhouseId(String slaughterhouseId) {
        this.slaughterhouseId = slaughterhouseId;
    }

    public String getSlaughterhouseName() {
        return slaughterhouseName;
    }

    public void setSlaughterhouseName(String slaughterhouseName) {
        this.slaughterhouseName = slaughterhouseName;
    }

    public String getSlaughterhouseLocation() {
        return slaughterhouseLocation;
    }

    public void setSlaughterhouseLocation(String slaughterhouseLocation) {
        this.slaughterhouseLocation = slaughterhouseLocation;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getAciderId() {
        return aciderId;
    }

    public void setAciderId(String aciderId) {
        this.aciderId = aciderId;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsLocation() {
        return logisticsLocation;
    }

    public void setLogisticsLocation(String logisticsLocation) {
        this.logisticsLocation = logisticsLocation;
    }

    public String getSupermarketId() {
        return supermarketId;
    }

    public void setSupermarketId(String supermarketId) {
        this.supermarketId = supermarketId;
    }

    public String getSupermarketName() {
        return supermarketName;
    }

    public void setSupermarketName(String supermarketName) {
        this.supermarketName = supermarketName;
    }

    public String getSupermarketLocation() {
        return supermarketLocation;
    }

    public void setSupermarketLocation(String supermarketLocation) {
        this.supermarketLocation = supermarketLocation;
    }

    public String getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(String salespersonId) {
        this.salespersonId = salespersonId;
    }
}
