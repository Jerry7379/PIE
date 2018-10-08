package com.sjcl.zrsy.dao.implement.sql;

import com.sjcl.zrsy.dao.IOperationDao;
import com.sjcl.zrsy.domain.dto.SearchId;
import com.sjcl.zrsy.domain.po.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SearchDao implements com.sjcl.zrsy.dao.ISearchDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IOperationDao operationDao;

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

    @Override
    public SearchId select(String id) {
        List<SearchId> list = jdbcTemplate.query("SELECT\n" +
                        "    id,\n" +
                        "    birthday,\n" +
                        "    breed,\n" +
                        "    gender,\n" +
                        "    isacid,\n" +
                        "    ischeck,\n" +
                        "    farm_id,\n" +
                        "    farm_location,\n" +
                        "    farm_name,\n" +
                        "    traceability_idcard.breeder_id,\n" +
                        "    slaughterhouse_id,\n" +
                        "    slaughterhouse_location,\n" +
                        "    slaughterhouse_name,\n" +
                        "    traceability_idcard.checker_id,\n" +
                        "    traceability_idcard.acider_id,\n" +
                        "    logistics_id,\n" +
                        "    logistics_name,\n" +
                        "    logistics_location,\n" +
                        "    supermarket_id,\n" +
                        "    supermarket_name,\n" +
                        "    supermarket_location,\n" +
                        "    salesperson_id\n" +
                        "FROM\n" +
                        "    traceability_idcard\n" +
                        "    LEFT JOIN pig_farm using(farm_id)\n" +
                        "    LEFT JOIN pig_slaughterhouse using(slaughterhouse_id)\n" +
                        "    LEFT JOIN pig_logistics using(logistics_id)\n" +
                        "    LEFT JOIN pig_supermarket using(supermarket_id)\n" +
                        "WHERE\n" +
                        "    id = ?", new Object[]{id}, SEARCHID_ROW_MAPPERR);


        if (list == null || list.size() == 0) {
            return null;
        } else {
            SearchId searchId = list.get(0);
            searchId.setOperations(searchOperation(id));
            return searchId;
        }
    }

    private List<Operation> searchOperation(String id) {
        return operationDao.findallOperationByPigid(id);
    }
}
