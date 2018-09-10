package com.sjcl.zrsy.service.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * this represents a data record.
 */
public class Record {
    private String tableName;

    private Map<String, Object> fields;

    public Record(String tableName) {
        this.tableName = tableName;
        this.fields = new HashMap<>();
    }

    public Record(String tableName, Map<String, Object> fields) {
        this.tableName = tableName;
        this.fields = new HashMap<>(fields);
    }

    public void addField(String fieldName, Object fieldValue) {
        this.fields.put(fieldName, fieldValue);
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Object> getFields() {
        return Collections.unmodifiableMap(fields);
    }

    public Object getFieldValue(String fieldName) {
        return this.fields.get(fieldName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(tableName, record.tableName) &&
                Objects.equals(fields, record.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, fields);
    }

    @Override
    public String toString() {
        return "Record{" +
                "tableName='" + tableName + '\'' +
                ", fields=" + fields +
                '}';
    }
}
