package com.sjcl.zrsy.service.data;

import com.sjcl.zrsy.Chain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

@Component
public class DataMaintainer {
    private static final Logger logger = LoggerFactory.getLogger(DataMaintainer.class);

    private static final List<String> tables = new ArrayList<>();
    static {
        tables.addAll(Arrays.asList("farm_operation",
                "logistics_operation",
                "market_operation",
                "pig_breeder",
                "pig_car",
                "pig_farm",
                "pig_logistics",
                "pig_slaughterhouse",
                "pig_supermarket",
                "registration",
                "slaughter_operation",
                "traceability_idcard"));
    }

    public static final String MARK_FIELD_NAME = "mark";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Chain chain;

    private String mark = null;

    public void maintain() {
        logger.info("maintain start");
        mark = generateMark();

        for (Record chainRecord : chain) {
            if (hasMark(chainRecord)){
                continue;
            }
            Record dbRecord = getDbRecord(chainRecord);

            if (dbRecord == null) {
                logger.info("find record in chain. but not in db, chain record: 【{}】", chainRecord);
                insert(chainRecord);
            } else if (chainRecord.equals(dbRecord)) {
                mark(dbRecord);
            } else {
                logger.info("find chain record is different from db, chain record: 【{}】, db record: 【{}】", chainRecord, dbRecord);
                repair(chainRecord);
            }
        }
        deleteUnmarked();
        mark = null;
        logger.info("maintain end");
    }

    private boolean hasMark(Record chainRecord) {
        String tableName = chainRecord.getTableName();
        String idFieldName = getIdFieldName(tableName);
        Object idFieldValue = chainRecord.getFieldValue(idFieldName);

        String mark = jdbcTemplate.queryForObject("SELECT " + MARK_FIELD_NAME + " FROM " + tableName + " WHERE " + idFieldName + " = ?", new Object[]{idFieldValue}, String.class);
        return Objects.equals(this.mark, mark);
    }

    private Record getDbRecord(Record chainRecord) {
        String tableName = chainRecord.getTableName();
        String idFieldName = getIdFieldName(tableName);
        Object idFieldValue = chainRecord.getFieldValue(idFieldName);

        List<Record> ret = jdbcTemplate.query("SELECT * FROM " + tableName + " WHERE " + idFieldName + " = ?", new Object[]{idFieldValue}, new RowMapper<Record>() {
            @Override
            public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
                Record dbRecord = new Record(chainRecord.getTableName());
                ResultSetMetaData metaData = rs.getMetaData();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String fieldName = metaData.getColumnName(i);
                    if (MARK_FIELD_NAME.equals(fieldName)) {
                        continue;
                    }
                    Object fieldValue = rs.getObject(fieldName);
                    dbRecord.addField(fieldName, fieldValue);
                }
                return dbRecord;
            }
        });

        if (ret != null && ret.size() > 0) {
            return ret.get(0);
        } else {
            return null;
        }
    }

    private String getIdFieldName(String tableName) {
        try {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet pkRs = metaData.getPrimaryKeys(null, null, tableName);
            if (pkRs.next()) {
                return pkRs.getString(4);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void insert(Record chainRecord) {
        StringBuilder sql = new StringBuilder("INSERT INTO ")
                .append(chainRecord.getTableName());

        Object[] fieldValues = new Object[chainRecord.getFields().size() + 1];

        StringJoiner fields = new StringJoiner(",", "(", ")");
        StringJoiner placeholders = new StringJoiner(",", "(", ")");

        int index = 0;
        for (Map.Entry<String, Object> fieldEntry : chainRecord.getFields().entrySet()) {
            fields.add(fieldEntry.getKey());
            placeholders.add("?");
            fieldValues[index] = fieldEntry.getValue();
            index++;
        }
        fields.add(MARK_FIELD_NAME);
        placeholders.add("?");
        fieldValues[index] = this.mark;

        sql
                .append(fields.toString())
                .append(" values ")
                .append(placeholders.toString());
        jdbcTemplate.update(sql.toString(), fieldValues);
    }

    private void mark(Record dbRecord) {
        String tableName = dbRecord.getTableName();
        String idFieldName = getIdFieldName(tableName);
        Object idFieldValue = dbRecord.getFieldValue(idFieldName);

        jdbcTemplate.update("UPDATE " + tableName + " SET " + MARK_FIELD_NAME + " = ? WHERE " + idFieldName + " = ?", mark, idFieldValue);


    }

    private void repair(Record chainRecord) {
        String tableName = chainRecord.getTableName();
        String idFieldName = getIdFieldName(tableName);
        Object idFieldValue = chainRecord.getFieldValue(idFieldName);

        jdbcTemplate.update("DELETE FROM " + tableName + " WHERE " + idFieldName + " = ?", idFieldValue);
        insert(chainRecord);
    }

    private void deleteUnmarked() {
        for (String tableName : tables) {
            jdbcTemplate.update("DELETE FROM " + tableName + " WHERE " + MARK_FIELD_NAME + " != ?", mark);
        }
    }

    private static String generateMark() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
