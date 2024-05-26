package com.filmbooking.repository.mapper;

import com.filmbooking.model.FailedLogin;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FailedLoginMapper implements RowMapper<FailedLogin> {
    @Override
    public FailedLogin map(ResultSet rs, StatementContext ctx) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new FailedLogin(
                rs.getString("req_ip"),
                rs.getInt("login_count"),
                LocalDateTime.parse(rs.getString("lock_time"), formatter)
        );
    }
}
