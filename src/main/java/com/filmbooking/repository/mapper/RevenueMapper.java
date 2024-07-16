package com.filmbooking.repository.mapper;

import com.filmbooking.model.Revenue;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RevenueMapper implements RowMapper<Revenue> {
    @Override
    public Revenue map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Revenue(
                rs.getString("film_name"),
                rs.getInt("booked_seats"),
                rs.getDouble("total_fee")
        );
    }
}
