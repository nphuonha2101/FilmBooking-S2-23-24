package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.repository.mapper.FilmBookingMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
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
                "username", filmBooking.getUser().getUsername(),
                "booking_date", filmBooking.getBookingDate(),
                "seats", String.join(",", filmBooking.getBookedSeats()),
                "total_fee", filmBooking.getTotalFee(),
                "payment_status", filmBooking.getPaymentStatus()
        );
    }
    public List<FilmBooking> sellectAllByShowtimeId(String username) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM film_bookings WHERE username = :username";
               return handle.createQuery(sql)
                    .bind("username", username)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    public List<FilmBooking> sellectAllByShowtimeId(long showtimeId) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM film_bookings WHERE showtime_id = :showtime_id";
            return handle.createQuery(sql)
                    .bind("showtime_id", showtimeId)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }
}
