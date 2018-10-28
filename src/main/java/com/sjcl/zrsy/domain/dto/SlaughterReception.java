package com.sjcl.zrsy.domain.dto;

import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

public class SlaughterReception {
    //猪id
    @Length(min = 13,max = 13,message = "猪id长度为13")
    private String id;
    //养殖场id
    @Length(min=6,max = 6,message = "养殖场ID长度为6")
    private String slaughterId;
    //检疫人员ID
    @Length(min = 18,max = 18,message = "检疫人员身份证ID长度18")
    private String checkerId;
    //是否检查通过
    @Size(min = 0,max = 1,message = "是否检查通过错误")
    private int isCheck;

    public SlaughterReception()
    {

    }

    public SlaughterReception(String id, String slaughterId, String checkerId, int isCheck) {
        this.id = id;
        this.slaughterId = slaughterId;
        this.checkerId = checkerId;
        this.isCheck = isCheck;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlaughterId() {
        return slaughterId;
    }

    public void setSlaughterId(String slaughterId) {
        this.slaughterId = slaughterId;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public TraceabilityIdcard toQuarantine() {
        TraceabilityIdcard quarantine = new TraceabilityIdcard();
        quarantine.setSlaughterhouseId(this.slaughterId);
        quarantine.setCheckerId(this.checkerId);
        quarantine.setIscheck(this.isCheck);
        quarantine.setId(this.id);
        return quarantine;
    }
}
