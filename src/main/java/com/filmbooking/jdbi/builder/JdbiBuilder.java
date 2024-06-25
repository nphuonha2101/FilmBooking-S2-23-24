package com.filmbooking.jdbi.builder;

import com.filmbooking.annotations.IdAutoIncrement;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.filmbooking.model.IModel;
import com.filmbooking.utils.annotation.ClazzAnnotationProcessor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class JdbiBuilder<T extends IModel> {
    @Getter
    private final String tableName;
    @Getter
    private final String primaryKeyName;
    private StringBuilder sql;
    @Getter
    private Map<String, Object> mapToRow;
    @Getter
    private final boolean isIdAutoIncrement;

    public JdbiBuilder(Class<T> modelClass) {
        ClazzAnnotationProcessor clazzAnnotationProcessor = ClazzAnnotationProcessor.getInstance(modelClass);
        this.tableName = (String) clazzAnnotationProcessor.getAnnotationValue(TableName.class, "value");
        this.primaryKeyName = (String) clazzAnnotationProcessor.getAnnotationValue(TableIdName.class, "value");
        this.isIdAutoIncrement = clazzAnnotationProcessor.isAnnotationPresent(IdAutoIncrement.class);

        System.out.println("JdbiBuilder: " + modelClass + " " + this.tableName + " " + this.primaryKeyName);
    }

    /**
     * Set the map to database row
     *
     * @param mapToRow map to database row
     */
    public void setMapToRow(Map<String, Object> mapToRow) {
        this.mapToRow = new HashMap<>(mapToRow);
    }

    /**
     * Build the insert SQL
     *
     * @return insert SQL
     */
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

    /**
     * Build the update SQL
     *
     * @return update SQL
     */
    public String buildUpdateSQL() {
        sql = new StringBuilder("UPDATE " + this.tableName + " SET ");

        for (String key : this.mapToRow.keySet()) {
            sql.append(key).append(" = :").append(key).append(", ");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
        sql.append(" WHERE ").append(this.primaryKeyName).append(" = ").append(":").append(this.primaryKeyName);

        return sql.toString();
    }

    /**
     * Build the delete SQL
     *
     * @return delete SQL
     */
    public String buildDeleteSQL() {
        return "DELETE FROM " + this.tableName + " WHERE " + this.primaryKeyName + " = :" + this.primaryKeyName;
    }

    /**
     * Build the select SQL
     *
     * @return select SQL
     */
    public String buildSelectSQL() {
        return "SELECT * FROM " + this.tableName + " WHERE " + this.primaryKeyName + " = :" + this.primaryKeyName;
    }

    /**
     * Build the select all SQL
     *
     * @return select all SQL
     */
    public String buildSelectAllSQL() {
        return "SELECT * FROM " + this.tableName;
    }

    /**
     * Build the select all SQL with filters
     *
     * @param filters filter with format "name_=" : value
     * @return select all SQL with filters
     */
    public String buildSelectAllSQL(Map<String, Object> filters) {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + this.tableName + " WHERE ");
        for (String key : filters.keySet()) {
            String[] keyParts = key.split("_");
            key = keyParts[0] + " " + keyParts[1];

            sql.append(key).append(" :").append(keyParts[0]).append(" AND ");
        }

        // Remove the last " AND "
        sql = new StringBuilder(sql.substring(0, sql.length() - 5));

        return sql.toString();
    }

    /**
     * Build the select all SQL with limit and offset
     *
     * @param limit  limit
     * @param offset offset
     * @return select all SQL with limit and offset
     */

    public String buildSelectAllSQL(int limit, int offset) {
        return "SELECT * FROM " + this.tableName + " LIMIT " + limit + " OFFSET " + offset;
    }

    /**
     * Build the select all SQL with limit, offset, and order
     *
     * @param limit  limit
     * @param offset offset
     * @param order  order with column name and direction. Example: "column_name ASC"
     * @return select all SQL with limit, offset, and order
     */
    public String buildSelectAllSQL(int limit, int offset, String order) {
        if (order == null)
            return "SELECT * FROM " + this.tableName + " LIMIT " + limit + " OFFSET " + offset;

        return "SELECT * FROM " + this.tableName + " ORDER BY " + order + " LIMIT " + limit + " OFFSET " + offset;
    }

    /**
     * Build the select all SQL with limit, offset, order, and filters
     *
     * @param limit   limit
     * @param offset  offset
     * @param order   order
     * @param filters filters
     * @return select all SQL with limit, offset, order, and filters
     */

    public String buildSelectAllSQL(int limit, int offset, String order, Map<String, Object> filters) {
        sql = new StringBuilder("SELECT * FROM " + this.tableName + " WHERE ");
        for (String key : filters.keySet()) {
            String keyParts[] = key.split("_");
            key = keyParts[0] + " " + keyParts[1];
            sql.append(key).append(" :").append(keyParts[0]).append(" AND ");
        }

        // Remove the last " AND "
        sql = new StringBuilder(sql.substring(0, sql.length() - 5));

        if (order != null) {
            sql.append(" ORDER BY ").append(order);
        }

        sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);
        return sql.toString();
    }

    /**
     * Build the count SQL
     * @return count SQL
     */
    public String buildCountSQL() {
        return "SELECT COUNT(*) FROM " + this.tableName;
    }
}
