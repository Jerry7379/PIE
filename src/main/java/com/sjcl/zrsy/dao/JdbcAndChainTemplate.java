package com.sjcl.zrsy.dao;

import com.alibaba.fastjson.JSONObject;
import com.sjcl.zrsy.domain.dto.IdCard;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcAndChainTemplate {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TXDao txDao;
    List<String> id;

    public boolean insert(String sql, @Nullable Object... args) throws DataAccessException, JSQLParserException {
        jdbcTemplate.update(sql, args);
        Statement statement = CCJSqlParserUtil.parse(sql);
        Insert insertStatment = (Insert) statement;
        String table = insertStatment.getTable().getName();//表名
        List<Column> colums = insertStatment.getColumns();//列名

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("table", table);
        id = jdbcTemplate.query("SELECT LAST_INSERT_ID()", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return resultSet.getString("LAST_INSERT_ID()");
            }
        });
        if (table.equals("traceability_idcard")) {
            String sql1 = "select * from " + table + "where id=" + id.get(0);
            List<IdCard> idCards = jdbcTemplate.query(sql1, new RowMapper<IdCard>() {
                @Override
                public IdCard mapRow(ResultSet rs, int rowNum) throws SQLException {
                    IdCard a = new IdCard();
                    a.setId(rs.getString("id"));
                    a.setBirthday(rs.getString("birthday"));
                    a.setBreed(rs.getString("breed"));
                    a.setGender(rs.getString("gender"));
                    a.setIsacid(rs.getString("isacid"));
                    a.setIshealth(rs.getString("ishealth"));
                    a.setAciderId(rs.getString("acider_id"));
                    a.setIscheck(rs.getString("ischeck"));
                    a.setCheckerId(rs.getString("checker_id"));
                    a.setFarmId(rs.getString("farm_id"));
                    a.setLogisticsId(rs.getString("logistics_id"));
                    a.setSlaughterhouseId(rs.getString("slaughterhouse_id"));
                    a.setSupermarketId(rs.getString("supermarket_id"));
                    a.setBreederId(rs.getString("breeder_id"));
                    a.setCarId(rs.getString("car_id"));
                    a.setDriverID(rs.getString("driver_id"));
                    a.setBirthweight(rs.getString("birthweight"));
                    return a;
                }
            });
            jsonObject.put("Field", idCards.get(0));
            if (jdbcTemplate.update("update ? set hash=? where id =?", table, txDao.sendTX(jsonObject), idCards.get(0).getId()) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            String sql1 = "select * from " + table + "where num=" + id.get(0);
            List<Map<String, Object>> maps = jdbcTemplate.query(sql1, new RowMapper<Map<String, Object>>() {
                @Override
                public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Map m = new HashMap();
                    for (int i = 0; i < colums.size(); i++) {
                        m.put(colums.get(i), rs.getString(i));
                    }
                    return m;
                }
            });
            jsonObject.put("Field", maps.get(0));
            if (jdbcTemplate.update("update ? set hash=? where num =?", table, txDao.sendTX(jsonObject), id.get(0)) == 1) {
                return true;
            } else {
                return false;
            }

        }
    }


    public boolean update(String sql, @Nullable Object... args) throws DataAccessException, JSQLParserException {
        jdbcTemplate.update(sql, args);
        Statement statement = CCJSqlParserUtil.parse(sql);
        Update a = (Update) statement;
        List<Table> table = a.getTables();
        List<Column> colums = a.getColumns();//列名
        Expression b=a.getWhere();
        String c[]=b.toString().split(":");

        String sql1 = "select * from " + table.get(0) + "where" + b;
        List<Map<String, Object>> maps = jdbcTemplate.query(sql1, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map m = new HashMap();
                for (int i = 0; i < colums.size(); i++) {
                    m.put(colums.get(i), rs.getObject(i));
                }
                return m;
            }
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("table", table.get(0));
        jsonObject.put("Field", maps.get(0));
        if (jdbcTemplate.update("update ? set hash=? where num =?", table, txDao.sendTX(jsonObject), c[1])==1) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("update traceability_idcard set gender=0 where id='1234567890123'");
        Update a = (Update) statement;
        List<Table> table = a.getTables();
        List<Column> colums = a.getColumns();//列名
        Expression b=a.getWhere();
        System.out.println(b.toString());
    }
}
