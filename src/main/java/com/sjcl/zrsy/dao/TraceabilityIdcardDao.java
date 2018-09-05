package com.sjcl.zrsy.dao;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
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
    public boolean getFarmCheck(String id) {
        List<String> records = jdbcTemplate.query("select id from traceability_idcard where id='"+id+"' limit 1", new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("id");
            }
        });
        return records != null && !records.isEmpty();
    }
}
