package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Genre;
import com.filmbooking.repository.mapper.GenresMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
import java.util.Map;

public class GenreRepository extends AbstractRepository<Genre> {
    public GenreRepository(Class<Genre> modelClass) {
        super(modelClass);
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
