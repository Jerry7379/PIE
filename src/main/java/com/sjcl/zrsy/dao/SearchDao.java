package com.sjcl.zrsy.dao;

import com.sjcl.zrsy.domain.SearchId;
import com.sjcl.zrsy.domain.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SearchDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<SearchId> SEARCHID_ROW_MAPPERR = new RowMapper<SearchId>() {
        @Override
        public SearchId mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            SearchId u = new SearchId();
            u.setId(resultSet.getString("Id"));
            u.setBirthday(resultSet.getString("Birthday"));
            u.setBreed(resultSet.getString("Breed"));
            u.setGender(resultSet.getString("Gender"));
            u.setIsacid(resultSet.getString("Isacid"));
            u.setIscheck(resultSet.getString("Ischeck"));

            u.setFarmId(resultSet.getString("FarmId"));
            u.setFarmLocation(resultSet.getString("FarmLocation"));
            u.setFarmName(resultSet.getString("FarmName"));
            u.setBreederId(resultSet.getString("BreederId"));

            u.setSlaughterhouseId(resultSet.getString("Slaughterhouse_id"));
            u.setSlaughterhouseLocation(resultSet.getString("Slaughterhouse_location"));
            u.setSlaughterhouseName(resultSet.getString("Slaughterhouse_name"));
            u.setCheckerId(resultSet.getString("Checker_id"));
            u.setAciderId(resultSet.getString("Acider_id"));

            u.setLogisticsId(resultSet.getString("Logistics_id"));
            u.setLogisticsName(resultSet.getString("Logistics_name"));
            u.setLogisticsLocation(resultSet.getString("Logistics_location"));

            u.setSupermarketId(resultSet.getString("Supermarket_id"));
            u.setSupermarketName(resultSet.getString("Supermarket_name"));
            u.setSupermarketLocation(resultSet.getString("Supermarket_location"));
            u.setSalespersonId(resultSet.getString("Salesperson_id"));
            return u;
        }
    };
    private static final RowMapper<SearchOperation> SEARCHID_ROW_MAPPERR1 = new RowMapper<SearchOperation>() {
        @Override
        public SearchOperation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            SearchOperation k = new SearchOperation();
            k.setOperation(resultSet.getString("Operation"));
            k.setContent(resultSet.getString("Content"));
            k.setRemark(resultSet.getString("Remark"));
            k.setTime(resultSet.getString("Time"));
            return k;
        }
    };
    public SearchId select(String id){
        List<SearchId> list = jdbcTemplate.query
                ("SELECT Id, Birthday, Breed, Gender, Isacid, Ischeck, " +
                        "FarmId, FarmLocation, FarmName, BreederId, " +
                        "Slaughterhouse_id, Slaughterhouse_location,Slaughterhouse_name, Checker_id, Acider_id, " +
                        "Logistics_id, Logistics_name, Logistics_location, " +
                        "Supermarket_id, Supermarket_name, Supermarket_location, Salesperson_id " +
                        "FROM pig_idcard LEFT JOIN pigfarm using(FarmId) " +
                        "LEFT JOIN pig_slaughterhouse using(Slaughterhouse_id)" +
                        "LEFT JOIN pig_logistics using(Logistics_id) " +
                        "LEFT JOIN pig_supermarket using(Supermarket_id) " +
                        "WHERE Id = ?", new Object[]{id}, SEARCHID_ROW_MAPPERR);
        if (list == null || list.size() == 0) {
            return null;
        } else {
            SearchId searchId = list.get(0);
            searchId.setOperations(searchOperation(id));
            return searchId;
        }
    }

    private List<SearchOperation> searchOperation(String id){
        List<SearchOperation> list1 = jdbcTemplate.query("SELECT operation, content, remark, time FROM pig_operation WHERE Id = ?", new Object[]{id}, SEARCHID_ROW_MAPPERR1);
        return list1;
    }
}
