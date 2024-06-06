package com.filmbooking.repository.mapper;

import com.filmbooking.model.LogModel;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogMapper implements RowMapper<LogModel> {
    @Override
    public LogModel map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LogModel(
                rs.getLong("log_id"),
                rs.getString("username"),
                rs.getString("req_ip"),
                rs.getString("ip_country"),
                rs.getString("log_level"),
                rs.getString("target_table"),
                rs.getString("actions"),
                rs.getBoolean("is_action_success"),
                rs.getString("before_data"),
                rs.getString("after_data"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at")
        );
    }
}
