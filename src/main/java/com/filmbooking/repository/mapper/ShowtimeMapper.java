package com.filmbooking.repository.mapper;

import com.filmbooking.model.Showtime;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowtimeMapper implements RowMapper<Showtime> {
    @Override
    public Showtime map(ResultSet rs, StatementContext ctx) throws SQLException {
        // TODO: Implement this method
//        return new Showtime(
//                rs.getLong("showtime_id"),
//                rs.getLong("film_id"),
//                rs.getLong("room_id"),
//                rs.getTimestamp("showtime_date").toLocalDateTime(),
//                rs.getString("seats_data"),
//                null,
//                rs.getString("slug")
//
//        );
        return null;
    }
}

