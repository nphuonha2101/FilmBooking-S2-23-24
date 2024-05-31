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
    private ShowtimeRepository showtimeRepository;
    private UserRepository userRepository;
    @Override
    public FilmBooking map(ResultSet rs, StatementContext ctx) throws SQLException {
        showtimeRepository = new ShowtimeRepository(Showtime.class);
        userRepository = new UserRepository(com.filmbooking.model.User.class);
        return new FilmBooking(
                showtimeRepository.select(rs.getLong("showtime_id")),
                userRepository.select(rs.getLong("user_id")),
                rs.getTimestamp("booking_date").toLocalDateTime(),
                rs.getString("booked_seats").split(","),
                rs.getDouble("total_fee")
        );
    }
}
