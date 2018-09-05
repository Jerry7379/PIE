package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.domain.dto.LogisticsReception;
import com.sjcl.zrsy.domain.dto.MarketReception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * TraceabilityIdcard数据访问对象
 */
public class TraceabilityIdcardDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 检查是否存在
     * @param id
     * @return
     */
    public boolean exsits(String id) {
        List<String> records = jdbcTemplate.query("select id from traceability_idcard where id='"+id+"' limit 1", new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("id");
            }
        });
        return records != null && !records.isEmpty();
    }

    /**
     * 插入 traceabilityIdcard
     * @param farmReception
     * @return
     */
    public boolean insertFarmReception(FarmReception farmReception) {
        try {
            jdbcTemplate.update("insert into  traceability_idcard(id,farm_id,breeder_id, birthday, breed, gender,birthweight) values  (?, ?, ?, ?, ?,?,?)",
                    farmReception.getId(), farmReception.getFarmId(),farmReception.getBreederId(), farmReception.getDate(), farmReception.getBreed(), farmReception.getGender(), farmReception.getWeight());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 插入物流公司收货操作
     * @param logisticsReception
     * @return
     */
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

    public boolean updateMarketReception(MarketReception marketReception){
        try {
            if(jdbcTemplate.update("UPDATE traceability_idcard SET supermarket_id = ? WHERE Id = ?", marketReception.getMarketId(), marketReception.getId())==1)
                return true;
            else
                return false;
        }
        catch (Exception e){
            return false;
        }
    }
}
