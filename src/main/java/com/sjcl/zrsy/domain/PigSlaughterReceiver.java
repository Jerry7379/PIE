package com.sjcl.zrsy.domain;

public class PigSlaughterReceiver {
    private String pigid;
    private String slaughterid;
    private String checkerid;
    private int ischeck;

    public PigSlaughterReceiver()
    {

    }

    public String getPigid() {
        return pigid;
    }

    public void setPigid(String pigid) {
        this.pigid = pigid;
    }

    public String getSlaughterid() {
        return slaughterid;
    }

    public void setSlaughterid(String slaughterid) {
        this.slaughterid = slaughterid;
    }

    public String getCheckerid() {
        return checkerid;
    }

    public void setCheckerid(String checkerid) {
        this.checkerid = checkerid;
    }

    public int getIscheck() {
        return ischeck;
    }

    public void setIscheck(int ischeck) {
        this.ischeck = ischeck;
    }
}
