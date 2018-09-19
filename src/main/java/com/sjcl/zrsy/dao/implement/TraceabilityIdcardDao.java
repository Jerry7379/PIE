package com.sjcl.zrsy.dao.implement;

import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class TraceabilityIdcardDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * check is exsits
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
     * insert traceabilityIdcard
     * @param initialFarm
     * @return
     */
    public boolean insert(TraceabilityIdcard initialFarm) {
        try {
            //insert语句使用 insert into 表名 set 字段名=‘’形式，插入主键的放在 set后第一个位置。
            jdbcTemplate.update("insert into  traceability_idcard set id=?,farm_id=?,breeder_id=?,birthday=?,breed=?,gender=?,birthweight=?",
                    initialFarm.getId(),
                    initialFarm.getFarmId(),
                    initialFarm.getBreederId(),
                    initialFarm.getBirthday(),
                    initialFarm.getBreed(),
                    initialFarm.getGender(),
                    initialFarm.getBirthweight());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * update logistics
     * @param logistics
     * @return
     */
    public boolean updateLogistics(TraceabilityIdcard logistics) {

        try {
            int updateResult = jdbcTemplate.update("UPDATE traceability_idcard SET logistics_id = ?, car_id = ?, driver_id = ? WHERE id = ?",
                    logistics.getLogisticsId(),
                    logistics.getCarId(),
                    logistics.getDriverId(),
                    logistics.getId());
            return updateResult == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * update market
     * @param market
     * @return
     */
    public boolean updateMarket(TraceabilityIdcard market){
        try {
            int updateResult = jdbcTemplate.update("UPDATE traceability_idcard SET supermarket_id = ? WHERE Id = ?", market.getSupermarketId(), market.getId());
            return updateResult == 1;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * update quarantine
     * @param quarantine
     * @return
     */
    public boolean updateQuarantine(TraceabilityIdcard quarantine)
    {
        try {
            int updateResult = jdbcTemplate.update("update traceability_idcard set slaughterhouse_id=?,checker_id=?,ischeck=? where id=?",
                    quarantine.getSlaughterhouseId(),
                    quarantine.getCheckerId(),
                    quarantine.getIscheck(),
                    quarantine.getId());
            return updateResult == 1;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * update acid
     * @param acid
     * @return
     */
    public boolean updateAcid(TraceabilityIdcard acid){
        int updatePigIdCardResult = jdbcTemplate.update("UPDATE traceability_idcard SET Acider_id = ?, Isacid = ? WHERE Id= ?", acid.getAciderId(), acid.getIsacid(), acid.getIsacid());
        return updatePigIdCardResult > 0;
    }
}
