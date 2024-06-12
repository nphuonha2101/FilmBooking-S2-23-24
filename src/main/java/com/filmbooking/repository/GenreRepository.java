package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.repository.mapper.GenresMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
import java.util.Map;

public class GenreRepository extends AbstractRepository<Genre> {
    public GenreRepository() {
        super(Genre.class);
    }

    public List<Genre> selectAllByFilmId(long filmId) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM genres WHERE genre_id IN (SELECT genre_id FROM film_genres WHERE film_id = :film_id)";
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

    public boolean deleteByFilmId(long filmId) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "DELETE FROM film_genres WHERE film_id = :film_id";
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
            if (!deleteByFilmId(film.getFilmID()))
                return false;

            List<Genre> genres = film.getGenreList();

            if (genres == null) {
                return true;
            }

            String sql = "INSERT INTO film_genres (film_id, genre_id) VALUES (:film_id, :genre_id)";
            Handle handle = JdbiDBConnection.openHandle();

            for (Genre genre : genres) {
                boolean updateResult = handle.createUpdate(sql)
                        .bind("film_id", film.getFilmID())
                        .bind("genre_id", genre.getGenreID())
                        .execute() == 1;

                if (!updateResult) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            JdbiDBConnection.closeHandle();
        }

        return true;
    }

    @Override
    RowMapper<Genre> getRowMapper() {
        return new GenresMapper();
    }

    @Override
    Map<String, Object> mapToRow(Genre genre) {
        return Map.of(
                "genre_id",genre.getGenreID(),
                "genre_name", genre.getGenreName()
        );
    }
}
