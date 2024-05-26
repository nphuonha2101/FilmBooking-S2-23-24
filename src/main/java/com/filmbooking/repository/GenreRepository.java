package com.filmbooking.repository;

import com.filmbooking.model.Genre;
import com.filmbooking.repository.mapper.GenresMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class GenreRepository extends AbstractRepository<Genre> {
    public GenreRepository(Class<Genre> modelClass) {
        super(modelClass);
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
