package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.domain.po.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OperationDao operationDao;

    private static final RowMapper<SearchId> SEARCHID_ROW_MAPPERR = new RowMapper<SearchId>() {
        @Override
        public SearchId mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            SearchId u = new SearchId();
            u.setId(resultSet.getString("id"));
            u.setBirthday(resultSet.getString("birthday"));
            u.setBreed(resultSet.getString("breed"));
            u.setGender(resultSet.getString("gender"));
            u.setIsacid(resultSet.getString("isacid"));
            u.setIscheck(resultSet.getString("ischeck"));

            u.setFarmId(resultSet.getString("farm_id"));
            u.setFarmLocation(resultSet.getString("farm_location"));
            u.setFarmName(resultSet.getString("farm_name"));
            u.setBreederId(resultSet.getString("breeder_id"));

            u.setSlaughterhouseId(resultSet.getString("slaughterhouse_id"));
            u.setSlaughterhouseLocation(resultSet.getString("slaughterhouse_location"));
            u.setSlaughterhouseName(resultSet.getString("slaughterhouse_name"));
            u.setCheckerId(resultSet.getString("checker_id"));
            u.setAciderId(resultSet.getString("acider_id"));

            u.setLogisticsId(resultSet.getString("logistics_id"));
            u.setLogisticsName(resultSet.getString("logistics_name"));
            u.setLogisticsLocation(resultSet.getString("logistics_location"));

            u.setSupermarketId(resultSet.getString("supermarket_id"));
            u.setSupermarketName(resultSet.getString("supermarket_name"));
            u.setSupermarketLocation(resultSet.getString("supermarket_location"));
            u.setSalespersonId(resultSet.getString("salesperson_id"));
            return u;
        }
    };

    public SearchId select(String id){
        List<SearchId> list = jdbcTemplate.query
                ("SELECT id, birthday, breed, gender, isacid, ischeck, " +
                        "farm_id, farm_location, farm_name, traceability_idcard.breeder_id, " +
                        "slaughterhouse_id, slaughterhouse_location,slaughterhouse_name, traceability_idcard.checker_id, traceability_idcard.acider_id, " +
                        "logistics_id, logistics_name, logistics_location, " +
                        "supermarket_id, supermarket_name, supermarket_location, salesperson_id " +
                        "FROM traceability_idcard LEFT JOIN pig_farm using(farm_id) " +
                        "LEFT JOIN pig_slaughterhouse using(slaughterhouse_id)" +
                        "LEFT JOIN pig_logistics using(logistics_id) " +
                        "LEFT JOIN pig_supermarket using(supermarket_id) " +
                        "WHERE id = ?", new Object[]{id}, SEARCHID_ROW_MAPPERR);
        if (list == null || list.size() == 0) {
            return null;
        } else {
            SearchId searchId = list.get(0);
            searchId.setOperations(searchOperation(id));
            return searchId;
        }
    }

    private List<Operation> searchOperation(String id){
        return operationDao.findallOperationByPigid(id);
    }
}
