package com.filmbooking.repository;

import com.filmbooking.model.FilmBooking;
import com.filmbooking.repository.mapper.FilmBookingMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class FilmBookingRepository extends AbstractRepository<FilmBooking>{
    public FilmBookingRepository(Class<FilmBooking> modelClass) {
        super(modelClass);
    }

    @Override
    RowMapper<FilmBooking> getRowMapper() {
        return new FilmBookingMapper();
    }

    @Override
    Map<String, Object> mapToRow(FilmBooking filmBooking) {
        return Map.of(
                "showtime_id", filmBooking.getShowtime().getShowtimeID(),
                "user_id", filmBooking.getUser().getUsername(),
                "booking_date", filmBooking.getBookingDate(),
                "booked_seats", String.join(",", filmBooking.getBookedSeats()),
                "total_fee", filmBooking.getTotalFee()
        );
    }
}
