package com.filmbooking.jdbi.builder;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.StringID;
import com.filmbooking.annotations.TableName;
import com.filmbooking.model.IModel;
import com.filmbooking.utils.annotation.ClazzAnnotationProcessor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class JdbiBuilder<T extends IModel> {
    @Getter
    private final String tableName;
    @Getter
    private final String primaryKeyName;
    private StringBuilder sql;
    @Setter
    @Getter
    private Map<String, Object> mapToRow;
    @Getter
    private final boolean isIdAutoIncrement;
    @Getter
    private final boolean isStringId;

    public JdbiBuilder(Class<T> modelClass) {
        ClazzAnnotationProcessor clazzAnnotationProcessor = ClazzAnnotationProcessor.getInstance(modelClass);
        this.tableName = (String) clazzAnnotationProcessor.getAnnotationValue(TableName.class, "value");
        this.primaryKeyName = (String) clazzAnnotationProcessor.getAnnotationValue(TableName.class, "value");
        this.isIdAutoIncrement = clazzAnnotationProcessor.isAnnotationPresent(IdAutoIncrement.class);
        this.isStringId = clazzAnnotationProcessor.isAnnotationPresent(StringID.class);
    }

    public String buildInsertSQL() {
        sql = new StringBuilder("INSERT INTO " + this.tableName + " (");

        if (this.isIdAutoIncrement) {
            this.mapToRow.remove(this.primaryKeyName);
        }

        for (String key : this.mapToRow.keySet()) {
            sql.append(key).append(", ");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
        sql.append(") VALUES (");

        for (String key : this.mapToRow.keySet()) {
            sql.append(":").append(key).append(", ");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
        sql.append(")");

        return sql.toString();
    }

    public String buildUpdateSQL() {
        sql = new StringBuilder("UPDATE " + this.tableName + " SET ");

        this.mapToRow.remove(this.primaryKeyName);

        for (String key : this.mapToRow.keySet()) {
            sql.append(key).append(" = :").append(key).append(", ");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
        sql.append(" WHERE ").append(this.primaryKeyName).append(" = ").append(":").append(this.primaryKeyName);

        return sql.toString();
    }

    public String buildDeleteSQL() {
        return "DELETE FROM " + this.tableName + " WHERE " + this.primaryKeyName + " = :" + this.primaryKeyName;
    }

    public String buildSelectSQL() {
        return "SELECT * FROM " + this.tableName + " WHERE " + this.primaryKeyName + " = :" + this.primaryKeyName;
    }

    public String buildSelectAllSQL() {
        return "SELECT * FROM " + this.tableName;
    }
}
