package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.*;
import com.filmbooking.repository.mapper.FilmMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
import java.util.Map;

public class FilmRepository extends AbstractRepository<Film>{

    public FilmRepository() {
        super(Film.class);
    }

    @Override
    public boolean delete(Film film) {
        if (!new ShowtimeRepository().deleteByFilmId(film.getFilmID()))
            return false;
        if (!new GenreRepository().deleteByFilmId(film.getFilmID()))
            return false;
        if (!new FilmVoteRepository().deleteByFilmId(film.getFilmID()))
            return false;

        return super.delete(film);
    }

    @Override
    public boolean update(Film film) {
        if (!new ShowtimeRepository().updateByFilm(film))
            return false;
        if (!new GenreRepository().updateByFilm(film))
            return false;
        if (!new FilmVoteRepository().updateByFilm(film))
            return false;

        return super.update(film);
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
        return Map.of(
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


}
