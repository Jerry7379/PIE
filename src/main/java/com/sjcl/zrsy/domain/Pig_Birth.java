package com.sjcl.zrsy.domain;

public class Pig_Birth {
    private String id;
    private String birthday;
    private String breed;
    private String gender;
    private String weight;

    public Pig_Birth()
    {
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id =id;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday =birthday;
    }

    public String getBreed()
    {
        return breed;
    }

    public void setBreed(String breed)
    {
        this.breed =breed;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender =gender;
    }

    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight =weight;
    }
}
