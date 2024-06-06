package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.repository.mapper.FilmMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
import java.util.Map;

public class FilmRepository extends AbstractRepository<Film>{

    public FilmRepository(Class<Film> modelClass) {
        super(modelClass);
    }

    @Override
    RowMapper<Film> getRowMapper() {
        return new FilmMapper();
    }

    @Override
    Map<String, Object> mapToRow(Film film) {
        return Map.of(
                "film_id", film.getFilmID(),
                "film_name", film.getFilmName(),
                "film_price", film.getFilmPrice(),
                "film_director", film.getDirector(),
                "film_cast", film.getCast(),
                "film_length", film.getFilmLength(),
                "film_description", film.getFilmDescription(),
                "film_trailer_link", film.getFilmTrailerLink(),
                "img_path", film.getImgPath(),
                "slug", film.getSlug()
        );
    }

    public List<Film> sellectAll(String genreId) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM films " +
                    "JOIN film_genres ON films.film_id = film_genres.film_id " +
                    "WHERE film_genres.genre_id = :genre_id";
            return handle.createQuery(sql)
                    .bind("genre_id", genreId)
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
