package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.FarmOperation;
import com.sjcl.zrsy.domain.LogisticsReceive;
import com.sjcl.zrsy.domain.LogisticsWork;
import com.sjcl.zrsy.domain.Pig_Birth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PigDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    //插入小猪出生信息
    public String insertBirth(Pig_Birth pigBirth) {

        //判断小猪id格式
        String pigId = pigBirth.getId();
        String regex = "^[0-9]{13}$";//id正则表达式
        if (!pigId.matches(regex)) {
            return "小猪id格式有误";
        }

        //判断养殖场id格式
        String farmId = pigBirth.getFarm_id();
        regex = "^[0-9]{6}$";//farm id正则表达式
        if (!farmId.matches(regex)) {
            return "养殖场id格式有误";
        }


        //判断breed的值是否符合规定
        String breedType = pigBirth.getBreed();
        if (!(breedType.equals("品种1") || breedType.equals("品种2") || breedType.equals("品种3") || breedType.equals("品种4"))) {
            return ("breedType格式：false");
        }

        //判断breed的值是否符合规定
        String genderType = pigBirth.getGender();
        if (!(genderType.equals("雌") || genderType.equals("雄"))) {
            return ("genderType格式：false");
        }

        //判断小猪出生体重格式是否符合正确
        String birthWeight = pigBirth.getWeight();
        regex = "^[0-9]{1,2}+(.[0-9]{2})?$";//小猪出生体重正则表达式
        if (!birthWeight.matches(regex) || birthWeight.equals("")) {
            return ("您输入的出生体重格式有误(精确到小数点后两位)" );
        }

        try {
            jdbcTemplate.update("insert into  pig_idcard(Id,Farm_id, Birthday, Breed, Gender,BirthWeight) values  (?, ?, ?, ?, ?,?)",
                    pigBirth.getId(), pigBirth.getFarm_id(), pigBirth.getDate(), pigBirth.getBreed(), pigBirth.getGender(), pigBirth.getWeight());
            return "填写成功";
        } catch (Exception e) {
            return "未知错误";
        }
    }


    //插入养殖场相关操作
    public String insertFPO(FarmOperation farmOperation) {

        //判断小猪id格式
        String pigId = farmOperation.getId();
        String regex = "^[0-9]{13}$";//id正则表达式
        if (!pigId.matches(regex)) {
            return "小猪id格式有误";
        }

        //判断operation的值是否符合规定
        String operationType = farmOperation.getOperation();
        if (!(operationType.equals("喂食") || operationType.equals("健康") || operationType.equals("防疫") || operationType.equals("出栏体重"))) {
            return ("operationType格式：false");
        }

        try {
            jdbcTemplate.update("insert into pig_operation(Id,Operation,Content,Remark,Time) values (?,?,?,?,?)",
                    farmOperation.getId(), farmOperation.getOperation(), farmOperation.getContent(), farmOperation.getRemark(), farmOperation.getTime());
            return "填写成功！";
        } catch (Exception e) {
            return "未知错误";
        }
    }


    //插入物流公司收货操作
    public String insertLR(LogisticsReceive logisticsReceive) {

        //判断小猪id格式
        String pigId = logisticsReceive.getId();
        String regex = "^[0-9]{13}$";//id正则表达式
        if (!pigId.matches(regex)) {
            return "小猪id格式有误";
        }

        //判断物流公司id格式
        String logisticsId = logisticsReceive.getLogisticsid();
        regex = "^[0-9]{6}$";//farm id正则表达式
        if (!logisticsId.matches(regex)) {
            return "物流公司id格式有误";
        }

        //判断车牌号格式是否符合正确
        String carId = logisticsReceive.getCarid();
        regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}+[A-Z]{1}+[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";//车牌号正则表达式
        if (!carId.matches(regex) || carId.equals("")) {
            return ("您输入的车牌号格式有误（如：京P1234A）" );
        }

        //判断司机id格式
        String driverId = logisticsReceive.getDriverid();
        regex = "^[0-9]{18}$";//司机 id正则表达式
        if (!driverId.matches(regex)) {
            return "司机id格式有误";
        }


        try {
            jdbcTemplate.update("UPDATE pig_idcard SET Logistics_id = ?, Car_id = ?, Driver_id = ? WHERE Id = ?",
                    logisticsReceive.getLogisticsid(), logisticsReceive.getCarid(), logisticsReceive.getDriverid(), logisticsReceive.getId());
            return "填写成功";
        } catch (Exception e) {
            return "未知错误";
        }
    }

    //物流运输过程检测温湿度
    public String insertLW(LogisticsWork logisticsWork) {

        //判断车牌号格式是否符合正确
        String carId = logisticsWork.getCarid();
        String regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}+[A-Z]{1}+[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";//车牌号正则表达式
        if (!carId.matches(regex) || carId.equals("")) {
            return ("您输入的车牌号格式有误（如：京P1234A）");
        }

        //判断运输湿度格式是否符合正确
        String humidity = logisticsWork.getHumidity();
        regex = "^[0-9]{1,2}+(.[0-9]{2})?$";//湿度正则表达式
        if (!humidity.matches(regex) || humidity.equals("")) {
            return ("您输入的湿度格式有误");
        }

        //判断运输温度格式是否符合正确
        String temperature = logisticsWork.getTemperature();
        regex = "^[0-9]{1,2}+(.[0-9]{2})?$";//温度正则表达式
        if (!temperature.matches(regex) || temperature.equals("")) {
            return ("您输入的温度格式有误");
        }

        //判断CO2浓度格式是否符合正确
        String co2 = logisticsWork.getCo2();
        regex = "^[0-9]{1,2}+(.[0-9]{2})?$";//CO2浓度正则表达式
        if (!co2.matches(regex) || co2.equals("")) {
            return ("您输入的CO2浓度格式有误");
        }

//        //判断经纬度格式是否符合正确
//        String location = logisticsWork.getLocation();
//        regex = "^E((\\d|[1-9]\\d|1[0-7]\\d)[\\s\\-度](\\d|[0-5]\\d)[\\s\\-分](\\d|[0-5]\\d)(\\.\\d{1,2})?[\\s\\-秒]?$)|(180[\\s\\-度]0[\\s\\-分]0[\\s\\-秒])N((\\d|[1-8]\\d)[\\s\\-度](\\d|[0-5]\\d)[\\s\\-分](\\d|[0-5]\\d)(\\.\\d{1,2})?[\\s\\-秒]?$)|(90[\\s\\-度]0[\\s\\-分]0[\\s\\-秒])$";//经纬度正则表达式
//        if (!location.matches(regex) || location.equals("")) {
//            return ("当前位置格式：" + location.matches(regex));
//        }

        String idA = logisticsWork.getId();
        String date[] = idA.split(";");
        int sum=0;
        regex = "^[0-9]{13}$";//id正则表达式
        for (int i = 0; i < date.length; i++) {
            //System.out.println(date[i]);
            if (!date[i].matches(regex)) {
                return "小猪id格式有误";
            }
            try {
                sum=sum+jdbcTemplate.update("insert into pig_transport (Id,Car_id,Humidity,Temperature,CO2,Location,Transport_time ) values (?,?,?,?,?,?,?)",
                        date[i],logisticsWork.getCarid(),logisticsWork.getHumidity(),logisticsWork.getTemperature(),logisticsWork.getCo2(),logisticsWork.getLocation(),logisticsWork.getTime());

            } catch (Exception e) {
                return e.toString();
            }
        }
        if(sum==date.length)
        {
            return "插入成功";
        }
        else{
            return "插入失败" ;
        }

    }
}
