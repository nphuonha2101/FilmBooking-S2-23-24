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

public class FilmBookingMapper implements RowMapper<FilmBooking> {
    @Override
    public FilmBooking map(ResultSet rs, StatementContext ctx) throws SQLException {
        ShowtimeRepository showtimeRepository = new ShowtimeRepository();
        UserRepository userRepository = new UserRepository();
        return new FilmBooking(
                showtimeRepository.select(rs.getLong("showtime_id")),
                userRepository.select(rs.getLong("username")),
                rs.getTimestamp("booking_date").toLocalDateTime(),
                rs.getString("booked_seats").split(","),
                rs.getDouble("total_fee")
        );
    }
}
