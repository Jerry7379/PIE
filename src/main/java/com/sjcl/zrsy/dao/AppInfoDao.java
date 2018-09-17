package com.sjcl.zrsy.dao;

import com.google.protobuf.ByteString;
import com.sjcl.zrsy.domain.po.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AppInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AppInfo load() {
        List<AppInfo> ret = jdbcTemplate.query("SELECT lastBlockHeight, lastBlockAppHash FROM  app_info", new RowMapper<AppInfo>() {
            @Override
            public AppInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                long height = rs.getLong("lastBlockHeight");
                String appHash = rs.getString("lastBlockAppHash");
                AppInfo appInfo = new AppInfo(height, ByteString.copyFrom(appHash, Charset.forName("utf-8")));
                return appInfo;
            }
        });
        if (ret != null && ret.size() == 1) {
            return ret.get(0);
        } else if (ret == null || ret.size() == 0) {
            return new AppInfo();
        } else {
            throw new IllegalStateException("appinfo just one");
        }
    }

    public void save(AppInfo appInfo) {
        long count = jdbcTemplate.queryForObject("SELECT count(*) FROM  app_info", long.class);
        if (count == 0) {
            String appHash = appInfo.getLastBlockAppHash().toStringUtf8();
            jdbcTemplate.update("INSERT  INTO app_info (lastBlockHeight, lastBlockAppHash) VALUES (?, ?)", appInfo.getLastBlockHeight(), appHash);
        } else if (count == 1) {
            String appHash = appInfo.getLastBlockAppHash().toStringUtf8();
            jdbcTemplate.update("UPDATE app_info SET lastBlockHeight = ?, lastBlockAppHash = ?", appInfo.getLastBlockHeight(), appHash);
        } else {
            throw new IllegalStateException("appinfo just one");
        }
    }
}
