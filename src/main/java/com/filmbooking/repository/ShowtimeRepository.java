package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.mapper.ShowtimeMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowtimeRepository extends AbstractRepository<Showtime> {
    public ShowtimeRepository() {
        super(Showtime.class);
    }

    public List<Showtime> selectAllByFilmId(long filmId) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM showtimes WHERE film_id = :film_id";
            System.out.println("Select all SQL: " + sql);
            return handle.createQuery(sql)
                    .bind("film_id", filmId)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    public List<Showtime> selectAllByRoomId(long roomID) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM showtimes WHERE room_id = :room_id";
            System.out.println("Select all SQL: " + sql);
            return handle.createQuery(sql)
                    .bind("room_id", roomID)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    public boolean deleteByFilmId(long filmId) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "DELETE FROM showtimes WHERE film_id = :film_id";
            System.out.println("Delete by film id SQL: " + sql);
            return handle.createUpdate(sql)
                    .bind("film_id", filmId)
                    .execute() == 1;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    public boolean updateByFilm(Film film) {
        try {
            deleteByFilmId(film.getFilmID());

            List<Showtime> showtimeList = film.getShowtimeList();
            if (showtimeList == null) {
                return true;
            }

            for (Showtime showtime : showtimeList) {
                insert(showtime);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    public boolean update(Showtime showtime) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "UPDATE showtimes SET film_id = :film_id, room_id = :room_id, showtime_date = :showtime_date, seats_data = :seats_data, slug = :slug WHERE showtime_id = :showtime_id";
            handle.createUpdate(sql)
                    .bind("showtime_id", showtime.getShowtimeID())
                    .bind("film_id", showtime.getFilm().getFilmID())
                    .bind("room_id", showtime.getRoom().getRoomID())
                    .bind("showtime_date", showtime.getShowtimeDate())
                    .bind("seats_data", showtime.getSeatsData())
                    .bind("slug", showtime.getSlug())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    RowMapper<Showtime> getRowMapper() {
        return new ShowtimeMapper();
    }

    @Override
    Map<String, Object> mapToRow(Showtime showtime) {
        Map<String, Object> result = new HashMap<>();
        result.put("film_id", showtime.getFilm().getFilmID());
        result.put("room_id", showtime.getRoom().getRoomID());
        result.put("showtime_date", showtime.getShowtimeDate());
        result.put("seats_data", showtime.getSeatsData());
        result.put("slug", showtime.getSlug());
        result.put("created_at", showtime.getCreatedAt());
        result.put("updated_at", showtime.getUpdatedAt());

        return result;
    }
}

