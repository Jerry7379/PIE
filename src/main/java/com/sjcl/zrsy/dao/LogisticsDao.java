package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.LogisticsReception;
import com.sjcl.zrsy.domain.LogisticsOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogisticsDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //插入物流公司收货操作
    public boolean updateLogisticsReception(LogisticsReception logisticsReception) {

        try {
            if(jdbcTemplate.update("UPDATE pig_idcard SET Logistics_id = ?, Car_id = ?, Driver_id = ? WHERE Id = ?",
                    logisticsReception.getLogisticsId(), logisticsReception.getCarId(), logisticsReception.getDriverId(), logisticsReception.getId())==1)
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    //物流运输过程检测温湿度
    public boolean insertLogisticsoperarion(LogisticsOperation logisticsOperation) {

        try {
            jdbcTemplate.update("insert into pig_transport (Id,Car_id,Humidity,Temperature,CO2,Location,Transport_time ) values (?,?,?,?,?,?,?)",
                    logisticsOperation.getId(), logisticsOperation.getCarId(), logisticsOperation.getHumidity(), logisticsOperation.getTemperature(), logisticsOperation.getCo2(), logisticsOperation.getLocation(), logisticsOperation.getTime());
            return true;
        } catch (Exception e) {
            return false;
        }


    }

}
