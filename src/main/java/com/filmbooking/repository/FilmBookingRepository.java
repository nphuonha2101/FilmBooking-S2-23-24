package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.repository.mapper.FilmBookingMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmBookingRepository extends AbstractRepository<FilmBooking>{
    public FilmBookingRepository() {
        super(FilmBooking.class);
    }

    @Override
    RowMapper<FilmBooking> getRowMapper() {
        return new FilmBookingMapper();
    }

    @Override
    Map<String, Object> mapToRow(FilmBooking filmBooking) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", filmBooking.getUser().getUsername());
        map.put("showtime_id", filmBooking.getShowtime().getIdValue());
        map.put("booking_date", filmBooking.getBookingDate());
        map.put("seats", String.join(",", filmBooking.getBookedSeats()));
        map.put("total_fee", filmBooking.getTotalFee());
        map.put("payment_status", filmBooking.getPaymentStatus());
        map.put("created_at", filmBooking.getCreatedAt());
        map.put("updated_at", filmBooking.getUpdatedAt());
        return map;
    }
    public List<FilmBooking> sellectAllByUsername(String username) {
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

    public boolean deleteByUsername(String username) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "DELETE FROM film_bookings WHERE username = :username";
            return handle.createUpdate(sql)
                    .bind("username", username)
                    .execute() == 1;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    public List<FilmBooking> selectAllByDates(String startDate, String endDate) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM film_bookings WHERE booking_date BETWEEN :start_date AND :end_date AND payment_status = 'paid'";
            return handle.createQuery(sql)
                    .bind("start_date", startDate)
                    .bind("end_date", endDate)
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
