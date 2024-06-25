package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.*;
import com.filmbooking.repository.mapper.FilmMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmRepository extends AbstractRepository<Film>{

    public FilmRepository() {
        super(Film.class);
    }

    public List<Film> selectAll(String genreId) {
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

    @Override
    RowMapper<Film> getRowMapper() {
        return new FilmMapper();
    }

    @Override
    Map<String, Object> mapToRow(Film film) {
        Map<String, Object> map = new HashMap<>();
        map.put("film_id", film.getFilmID());
        map.put("film_name", film.getFilmName());
        map.put("film_price", film.getFilmPrice());
        map.put("film_director", film.getDirector());
        map.put("film_cast", film.getCast());
        map.put("film_length", film.getFilmLength());
        map.put("film_description", film.getFilmDescription());
        map.put("film_trailer_link", film.getFilmTrailerLink());
        map.put("img_path", film.getImgPath());
        map.put("slug", film.getSlug());
        return map;
    }


}
