package com.filmbooking.repository;

import com.filmbooking.cache.CacheManager;
import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.repository.mapper.GenresMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreRepository extends AbstractRepository<Genre> {
    private CacheManager cacheManager;

    public GenreRepository(CacheManager cacheManager) {
        super(Genre.class);
        this.cacheManager = cacheManager;
    }

    public GenreRepository() {
        super(Genre.class);
    }

    @SuppressWarnings("unchecked")
    public List<Genre> selectAllByFilmId(long filmId) {
        if (cacheManager != null) {
            List<Genre> genres = (List<Genre>) cacheManager.get("genres_by_film_id_" + filmId);
            System.out.println("Cache genres: " + genres);
            if (genres != null) {
                return genres;
            }
        }

        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM genres WHERE genre_id IN (SELECT genre_id FROM film_genres WHERE film_id = :film_id)";
            System.out.println("Select all SQL: " + sql);
            List<Genre> genreList = handle.createQuery(sql)
                    .bind("film_id", filmId)
                    .map(getRowMapper())
                    .list();

            if (cacheManager != null) {
                cacheManager.put("genres_by_film_id_" + filmId, genreList);
            }

            return genreList;

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
                    .execute() >= 1;
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

            List<Genre> genres = film.getGenreList();

            System.out.println("Genre Repository - updateByFilm: " + genres);

            if (genres == null || genres.isEmpty()) {
                return true;
            }

            String sql = "INSERT INTO film_genres (film_id, genre_id) VALUES (:film_id, :genre_id)";
            Handle handle = JdbiDBConnection.openHandle();

            for (Genre genre : genres) {
                handle.createUpdate(sql)
                        .bind("film_id", film.getFilmID())
                        .bind("genre_id", genre.getGenreID())
                        .execute();
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
        Map<String, Object> result = new HashMap<>();
        result.put("genre_id", genre.getGenreID());
        result.put("genre_name", genre.getGenreName());

        return result;
    }
}
