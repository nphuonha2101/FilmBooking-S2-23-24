package com.filmbooking.repository;

import com.filmbooking.model.Showtime;
import com.filmbooking.repository.mapper.ShowtimeMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class ShowtimeRepository extends AbstractRepository<Showtime> {
    public ShowtimeRepository(Class<Showtime> modelClass) {
        super(modelClass);
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

