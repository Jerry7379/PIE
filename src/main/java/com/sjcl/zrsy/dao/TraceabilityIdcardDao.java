package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.FarmReception;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * TraceabilityIdcard数据访问对象
 */
@Component
public class TraceabilityIdcardDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    JdbcAndChainTemplate jdbcAndChainTemplate;

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
     * @param initialFarm
     * @return
     */
    public boolean insert(TraceabilityIdcard initialFarm) {
        try {
            //insert语句使用 insert into 表名 set 字段名=‘’形式，插入主键的放在 set后第一个位置。
            jdbcAndChainTemplate.insert("insert into  traceability_idcard set id='"+initialFarm.getId()+
                            "',farm_id='"+initialFarm.getFarmId()+
                            "',breeder_id='"+initialFarm.getBreederId()+
                            "',birthday='"+initialFarm.getBirthday()+
                            "',breed='"+initialFarm.getBreed()+
                            "',gender='"+initialFarm.getGender()+
                            "',birthweight='"+initialFarm.getBirthweight()+"'");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 插入物流公司收货操作
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
     * 屠宰场检疫（还没数据检查）
     * @param quarantine
     * @returnSlaughterHouseServiceImpl
     */
    public boolean updateQuarantine(TraceabilityIdcard quarantine)
    {
        try {
            return  jdbcAndChainTemplate.update("update traceability_idcard set slaughterhouse_id='"+quarantine.getSlaughterhouseId()+
                            "',checker_id='"+quarantine.getCheckerId()+
                            "',ischeck='"+quarantine.getIscheck()+
                            "' where id='"+quarantine.getId()+"'"
            );

        }catch (Exception e){
            return false;
        }
    }

    public boolean updateAcid(TraceabilityIdcard acid){
        int updatePigIdCardResult = jdbcTemplate.update("UPDATE traceability_idcard SET Acider_id = ?, Isacid = ? WHERE Id= ?", acid.getAciderId(), acid.getIsacid(), acid.getIsacid());
        return updatePigIdCardResult > 0;
    }
}
