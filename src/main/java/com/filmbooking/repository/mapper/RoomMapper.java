package com.filmbooking.repository.mapper;

import com.filmbooking.model.Room;
import com.filmbooking.model.Theater;
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
        theaterRepository = new TheaterRepository(Theater.class);

        return new Room(
                rs.getLong("room_id"),
                rs.getString("room_name"),
                rs.getInt("seat_rows"),
                rs.getInt("seat_cols"),
                StringUtils.convertTo2DArr(rs.getInt("seat_rows") + " " + rs.getInt("seat_cols")),
                rs.getString("seats_data"),
                theaterRepository.select(rs.getLong("theater_id")),
                null,
                rs.getString("slug")
        );

    }

}
