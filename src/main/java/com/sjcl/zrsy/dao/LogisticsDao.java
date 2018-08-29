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
            if(jdbcTemplate.update("UPDATE traceability_idcard SET logistics_id = ?, car_id = ?, driver_id = ? WHERE id = ?",
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
            jdbcTemplate.update("insert into logistics_operation (id,Car_id,humidity,temperature,co2,location,transport_time ) values (?,?,?,?,?,?,?)",
                    logisticsOperation.getId(), logisticsOperation.getCarId(), logisticsOperation.getHumidity(), logisticsOperation.getTemperature(), logisticsOperation.getCo2(), logisticsOperation.getLocation(), logisticsOperation.getTime());
            return true;
        } catch (Exception e) {
            return false;
        }


    }

}
