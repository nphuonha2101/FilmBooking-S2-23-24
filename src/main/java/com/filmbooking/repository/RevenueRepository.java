package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Revenue;
import com.filmbooking.repository.mapper.RevenueMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RevenueRepository extends AbstractRepository<Revenue> {
    public RevenueRepository() {
        super(Revenue.class);
    }

    @Override
    RowMapper<Revenue> getRowMapper() {
        return new RevenueMapper();
    }

    @Override
    Map<String, Object> mapToRow(Revenue revenue) {
        Map<String, Object> result = new HashMap<>();
        result.put("film_name", revenue.getRevenueName());
        result.put("booked_seats", revenue.getTicketSold());
        result.put("total_fee", revenue.getTotalRevenue());
        return result;
    }

    public Revenue selectAllByMonth(String month, String year) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT SUM(LENGTH(seats) - LENGTH(REPLACE(seats, ',', '')) + 1) AS ticket_count, SUM(total_fee) AS total_revenue " +
                    "FROM film_bookings WHERE EXTRACT(MONTH FROM booking_date) = :month AND EXTRACT(YEAR FROM booking_date) = :year AND payment_status = 'paid'";
            return handle.createQuery(sql)
                    .bind("month", month)
                    .bind("year", year)
                    .map((rs, ctx) -> new Revenue(month + "/" + year, rs.getInt("ticket_count"), rs.getDouble("total_revenue")))
                    .first();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return new Revenue(month + "/" + year, 0, 0);
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    public Revenue selectAllByYear(String year) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT SUM(LENGTH(seats) - LENGTH(REPLACE(seats, ',', '')) + 1) AS ticket_count, SUM(total_fee) AS total_revenue " +
                    "FROM film_bookings WHERE EXTRACT(YEAR FROM booking_date) = :year AND payment_status = 'paid'";
            return handle.createQuery(sql)
                    .bind("year", year)
                    .map((rs, ctx) -> new Revenue(year, rs.getInt("ticket_count"), rs.getDouble("total_revenue")))
                    .first();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return new Revenue(year, 0, 0);
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    public List<Revenue> getByDates(String dateStart, String dateEnd) {
        List<Revenue> revenues = new ArrayList<>();
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT f.film_name, " +
                    "SUM(LENGTH(fb.seats) - LENGTH(REPLACE(fb.seats, ',', '')) + 1) AS ticket_count, " +
                    "SUM(fb.total_fee) AS total_revenue " +
                    "FROM film_bookings fb " +
                    "JOIN showtimes s ON fb.showtime_id = s.showtime_id " +
                    "JOIN films f ON s.film_id = f.film_id " +
                    "WHERE fb.booking_date BETWEEN :start_date AND :end_date " +
                    "AND fb.payment_status = 'paid' " +
                    "GROUP BY f.film_name";
            revenues = handle.createQuery(sql)
                    .bind("start_date", dateStart)
                    .bind("end_date", dateEnd)
                    .map((rs, ctx) -> new Revenue(rs.getString("film_name"), rs.getInt("ticket_count"), rs.getDouble("total_revenue")))
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            revenues.add(new Revenue("None", 0, 0));
        } finally {
            JdbiDBConnection.closeHandle();
        }
        return revenues;
    }
}
