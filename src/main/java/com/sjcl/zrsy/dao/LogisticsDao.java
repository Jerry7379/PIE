package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.LogisticsReceive;
import com.sjcl.zrsy.domain.LogisticsOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogisticsDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //插入物流公司收货操作
    public boolean updateLogisticsReception(LogisticsReceive logisticsReceive) {
//
//        //判断小猪id格式
//        String pigId = logisticsReceive.getId();
//        String regex = "^[0-9]{13}$";//id正则表达式
//        if (!pigId.matches(regex)) {
//            return "小猪id格式有误";
//        }
//
//        //判断物流公司id格式
//        String logisticsId = logisticsReceive.getLogisticsid();
//        regex = "^[0-9]{6}$";//farm id正则表达式
//        if (!logisticsId.matches(regex)) {
//            return "物流公司id格式有误";
//        }
//
//        //判断车牌号格式是否符合正确
//        String carId = logisticsReceive.getCarid();
//        regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}+[A-Z]{1}+[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";//车牌号正则表达式
//        if (!carId.matches(regex) || carId.equals("")) {
//            return ("您输入的车牌号格式有误（如：京P1234A）" );
//        }
//
//        //判断司机id格式
//        String driverId = logisticsReceive.getDriverid();
//        regex = "^[0-9]{18}$";//司机 id正则表达式
//        if (!driverId.matches(regex)) {
//            return "司机id格式有误";
//        }
        try {
            if(jdbcTemplate.update("UPDATE pig_idcard SET Logistics_id = ?, Car_id = ?, Driver_id = ? WHERE Id = ?",
                    logisticsReceive.getLogisticsId(), logisticsReceive.getCarId(), logisticsReceive.getDriverId(), logisticsReceive.getId())==1)
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    //物流运输过程检测温湿度
    public boolean insertLogisticsoperarion(LogisticsOperation logisticsWork) {
//        //判断车牌号格式是否符合正确
//        String carId = logisticsWork.getCarid();
//        String regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}+[A-Z]{1}+[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";//车牌号正则表达式
//        if (!carId.matches(regex) || carId.equals("")) {
//            return ("您输入的车牌号格式有误（如：京P1234A）");
//        }
//
//        //判断运输湿度格式是否符合正确
//        String humidity = logisticsWork.getHumidity();
//        regex = "^[0-9]{1,2}+(.[0-9]{2})?$";//湿度正则表达式
//        if (!humidity.matches(regex) || humidity.equals("")) {
//            return ("您输入的湿度格式有误");
//        }
//
//        //判断运输温度格式是否符合正确
//        String temperature = logisticsWork.getTemperature();
//        regex = "^[0-9]{1,2}+(.[0-9]{2})?$";//温度正则表达式
//        if (!temperature.matches(regex) || temperature.equals("")) {
//            return ("您输入的温度格式有误");
//        }
//
//        //判断CO2浓度格式是否符合正确
//        String co2 = logisticsWork.getCo2();
//        regex = "^[0-9]{1,2}+(.[0-9]{2})?$";//CO2浓度正则表达式
//        if (!co2.matches(regex) || co2.equals("")) {
//            return ("您输入的CO2浓度格式有误");
//        }
//
////        //判断经纬度格式是否符合正确
////        String location = logisticsWork.getLocation();
////        regex = "^E((\\d|[1-9]\\d|1[0-7]\\d)[\\s\\-度](\\d|[0-5]\\d)[\\s\\-分](\\d|[0-5]\\d)(\\.\\d{1,2})?[\\s\\-秒]?$)|(180[\\s\\-度]0[\\s\\-分]0[\\s\\-秒])N((\\d|[1-8]\\d)[\\s\\-度](\\d|[0-5]\\d)[\\s\\-分](\\d|[0-5]\\d)(\\.\\d{1,2})?[\\s\\-秒]?$)|(90[\\s\\-度]0[\\s\\-分]0[\\s\\-秒])$";//经纬度正则表达式
////        if (!location.matches(regex) || location.equals("")) {
////            return ("当前位置格式：" + location.matches(regex));
////        }
//
//        String idA = logisticsWork.getId();
//        String date[] = idA.split(";");
//        int sum=0;
//        regex = "^[0-9]{13}$";//id正则表达式
//        for (int i = 0; i < date.length; i++) {
//            //System.out.println(date[i]);
//            if (!date[i].matches(regex)) {
//                return "小猪id格式有误";
//            }
        try {
            jdbcTemplate.update("insert into pig_transport (Id,Car_id,Humidity,Temperature,CO2,Location,Transport_time ) values (?,?,?,?,?,?,?)",
                    logisticsWork.getId(), logisticsWork.getCarId(), logisticsWork.getHumidity(), logisticsWork.getTemperature(), logisticsWork.getCo2(), logisticsWork.getLocation(), logisticsWork.getTime());
            return true;
        } catch (Exception e) {
            return false;
        }


    }

}
