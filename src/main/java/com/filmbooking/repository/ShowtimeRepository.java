package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.mapper.ShowtimeMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
import java.util.Map;

public class ShowtimeRepository extends AbstractRepository<Showtime> {
    public ShowtimeRepository(Class<Showtime> modelClass) {
        super(modelClass);
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

    public Showtime selectAllByRoomId(long roomID) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM showtimes WHERE room_id = :room_id";
            System.out.println("Select all SQL: " + sql);
            return handle.createQuery(sql)
                    .bind("room_id", roomID)
                    .map(getRowMapper())
                    .one();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    @Override
    RowMapper<Showtime> getRowMapper() {
        return new ShowtimeMapper();
    }

    @Override
    Map<String, Object> mapToRow(Showtime showtime) {
        return Map.of(
                "showtime_id", showtime.getShowtimeID(),
                "film_id", showtime.getFilm().getFilmID(),
                "room_id", showtime.getRoom().getRoomID(),
                "showtime_date", showtime.getShowtimeDate(),
                "seats_data", showtime.getSeatsData(),
                "slug", showtime.getSlug()
        );
    }
}

