package com.filmbooking.repository.mapper;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.FilmBookingRepository;
import com.filmbooking.repository.FilmRepository;
import com.filmbooking.repository.RoomRepository;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowtimeMapper implements RowMapper<Showtime> {
    @Override
    public Showtime map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Showtime(
                rs.getLong("showtime_id"),
                rs.getLong("film_id"),
                rs.getLong("room_id"),
                rs.getTimestamp("showtime_date").toLocalDateTime(),
                rs.getString("seats_data"),
                rs.getString("slug"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}

