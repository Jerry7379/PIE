package com.sjcl.zrsy.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sjcl.zrsy.domain.dto.IdCard;
import com.sjcl.zrsy.domain.dto.JsonRpcRequest;
import com.sjcl.zrsy.domain.dto.JsonRpcRespones;
import com.sjcl.zrsy.domain.dto.JsonRpcResponesResult;
import com.sjcl.zrsy.domain.po.TraceabilityIdcard;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcAndChainTemplate {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    RestTemplate restTemplate;

    private String url;

    public JdbcAndChainTemplate(@Value("${chain_url}") String url) {
        this.url = url;
    }


    List<String> id;//
    List<Expression> expressions;//

    public boolean insert(String sql) throws DataAccessException, JSQLParserException {
        jdbcTemplate.update(sql);

        Statement statement = CCJSqlParserUtil.parse(sql);
        Insert insertStatment = (Insert) statement;
        String table = insertStatment.getTable().getName();//表名
        List<Column> colums = insertStatment.getColumns();//列名
        expressions=insertStatment.getSetExpressionList();


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("table", table);
//        id = jdbcTemplate.query("SELECT LAST_INSERT_ID()", new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//                return resultSet.getString("LAST_INSERT_ID()");
//            }
//        });
        if (table.equals("traceability_idcard")) {
            String sql1 = "select * from " + table + " where id=" + expressions.get(0);
            List<TraceabilityIdcard> idCards = jdbcTemplate.query(sql1, new RowMapper<TraceabilityIdcard>() {
                @Override
                public TraceabilityIdcard mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TraceabilityIdcard a = new TraceabilityIdcard();
                    a.setId(rs.getString("id"));
                    a.setBirthday(rs.getDate("birthday"));
                    a.setBreed(rs.getString("breed"));
                    a.setGender(rs.getString("gender"));
                    a.setIsacid(rs.getInt("isacid"));
                    a.setIshealth(rs.getInt("ishealth"));
                    a.setAciderId(rs.getString("acider_id"));
                    a.setIscheck(rs.getInt("ischeck"));
                    a.setCheckerId(rs.getString("checker_id"));
                    a.setFarmId(rs.getString("farm_id"));
                    a.setLogisticsId(rs.getString("logistics_id"));
                    a.setSlaughterhouseId(rs.getString("slaughterhouse_id"));
                    a.setSupermarketId(rs.getString("supermarket_id"));
                    a.setBreederId(rs.getString("breeder_id"));
                    a.setCarId(rs.getString("car_id"));
                    a.setDriverId(rs.getString("driver_id"));
                    a.setBirthweight(rs.getDouble("birthweight"));
                    return a;
                }
            });
            jsonObject.put("Field", idCards.get(0));
            if (jdbcTemplate.update("update "+table+" set hash=? where id =?", sendTX(jsonObject), idCards.get(0).getId()) == 1) {
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
            if (jdbcTemplate.update("update ? set hash=? where num =?", table, sendTX(jsonObject), id.get(0)) == 1) {
                return true;
            } else {
                return false;
            }

        }
    }


    public boolean update(String sql) throws DataAccessException, JSQLParserException {
        jdbcTemplate.update(sql);
        Statement statement = CCJSqlParserUtil.parse(sql);
        Update a = (Update) statement;
        List<Table> table = a.getTables();
        List<Column> colums = a.getColumns();//列名
        Expression b=a.getWhere();


        String sql1 = "select * from " + table.get(0) + " where " + b;
        List<TraceabilityIdcard> maps = jdbcTemplate.query(sql1, new RowMapper<TraceabilityIdcard>() {
            @Override
            public TraceabilityIdcard mapRow(ResultSet rs, int rowNum) throws SQLException {
                TraceabilityIdcard a = new TraceabilityIdcard();
                a.setId(rs.getString("id"));
                a.setBirthday(rs.getDate("birthday"));
                a.setBreed(rs.getString("breed"));
                a.setGender(rs.getString("gender"));
                a.setIsacid(rs.getInt("isacid"));
                a.setIshealth(rs.getInt("ishealth"));
                a.setAciderId(rs.getString("acider_id"));
                a.setIscheck(rs.getInt("ischeck"));
                a.setCheckerId(rs.getString("checker_id"));
                a.setFarmId(rs.getString("farm_id"));
                a.setLogisticsId(rs.getString("logistics_id"));
                a.setSlaughterhouseId(rs.getString("slaughterhouse_id"));
                a.setSupermarketId(rs.getString("supermarket_id"));
                a.setBreederId(rs.getString("breeder_id"));
                a.setCarId(rs.getString("car_id"));
                a.setDriverId(rs.getString("driver_id"));
                a.setBirthweight(rs.getDouble("birthweight"));
                return a;
            }
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("table", table.get(0));
        jsonObject.put("Field", maps.get(0));
        if (jdbcTemplate.update("update "+table.get(0)+" set hash='"+sendTX(jsonObject)+"' where "+b.toString())==1) {
            return true;
        } else {
            return false;
        }
    }



    public String sendTX(Object object) throws JSONException {
        JsonRpcRequest jsonRpcRequest = buildBroadcatTxRequest(object);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, JSON.toJSONString(jsonRpcRequest),JSONObject.class);
        JsonRpcRespones jsonRpcRespones =JSON.parseObject(responseEntity.getBody().toString(),JsonRpcRespones.class);
        JsonRpcResponesResult jsonRpcResponesResult=JSON.parseObject(jsonRpcRespones.getResult().toString(),JsonRpcResponesResult.class);
        return jsonRpcResponesResult.getHash();
    }


    private JsonRpcRequest buildBroadcatTxRequest(Object object) {
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest(String.valueOf(object.hashCode()), "broadcast_tx_commit");
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] strBytes = JSON.toJSONString(object).getBytes();
        String encodedStr=encoder.encodeToString(strBytes);
        Map m=new HashMap();
        m.put("tx",encodedStr);
        jsonRpcRequest.setParams(m);
        return jsonRpcRequest;
    }



}
