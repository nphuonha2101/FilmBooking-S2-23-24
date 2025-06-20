package com.filmbooking.repository.mapper;

import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.Theater;
import com.filmbooking.repository.ShowtimeRepository;
import com.filmbooking.repository.TheaterRepository;
import com.filmbooking.utils.StringUtils;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
     TheaterRepository theaterRepository;
    @Override
    public Room map(ResultSet rs, StatementContext ctx) throws SQLException {
        theaterRepository = new TheaterRepository();

        return new Room(
                rs.getLong("room_id"),
                rs.getString("room_name"),
                rs.getInt("seat_rows"),
                rs.getInt("seat_cols"),
                rs.getString("seats_data"),
                rs.getLong("theater_id"),
                rs.getString("slug"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );

    }

}
