package com.filmbooking.repository.mapper;

import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.FilmRepository;
import com.filmbooking.repository.ShowtimeRepository;
import com.filmbooking.repository.UserRepository;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FilmBookingMapper implements RowMapper<FilmBooking> {
    @Override
    public FilmBooking map(ResultSet rs, StatementContext ctx) throws SQLException {
        ShowtimeRepository showtimeRepository = new ShowtimeRepository();
        UserRepository userRepository = new UserRepository();
        return new FilmBooking(
                rs.getLong("film_booking_id"),
                rs.getString("username"),
                rs.getLong("showtime_id"),
                rs.getTimestamp("booking_date").toLocalDateTime(),
                rs.getString("seats").split(","),
                rs.getDouble("total_fee"),
                rs.getString("payment_status"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
