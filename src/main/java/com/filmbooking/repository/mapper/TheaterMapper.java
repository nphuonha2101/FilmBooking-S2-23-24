package com.filmbooking.repository.mapper;

import com.filmbooking.model.Room;
import com.filmbooking.model.Theater;
import com.filmbooking.repository.RoomRepository;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TheaterMapper implements RowMapper<Theater> {
    @Override
    public Theater map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Theater(
                rs.getLong("theater_id"),
                rs.getString("theater_name"),
                rs.getString("tax_code"),
                rs.getString("theater_address"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
