package com.filmbooking.repository.mapper;

import com.filmbooking.model.Room;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Room(
                rs.getString("room_name"),
//                rs.getInt("theater_id"),
                rs.getInt("seat_rows"),
                rs.getInt("seat_cols"),
                rs.getString("seats_data"),
                rs.getString("slug")
        );

    }

}
