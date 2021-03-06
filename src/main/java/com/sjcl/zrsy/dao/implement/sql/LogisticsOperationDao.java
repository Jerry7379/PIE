package com.sjcl.zrsy.dao.implement.sql;

import com.sjcl.zrsy.domain.dto.LogisticsOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class LogisticsOperationDao implements com.sjcl.zrsy.dao.ILogisticsOperationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * insert logistics operation
     * @param logisticsOperation
     * @return
     */
    @Override
    public boolean insertLogisticsOperation(LogisticsOperation logisticsOperation) {

        try {
            jdbcTemplate.update("insert into logistics_operation (id,Car_id,humidity,temperature,co2,location,transport_time ) values (?,?,?,?,?,?,?)",
                    logisticsOperation.getId(),
                    logisticsOperation.getCarId(),
                    logisticsOperation.getHumidity(),
                    logisticsOperation.getTemperature(),
                    logisticsOperation.getCo2(),
                    logisticsOperation.getLocation(),
                    logisticsOperation.getTransportTime()
            );

            return true;
        } catch (Exception e) {
            return false;
        }


    }

}
