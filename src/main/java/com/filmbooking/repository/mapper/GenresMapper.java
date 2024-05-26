package com.filmbooking.repository.mapper;

import com.filmbooking.model.Genre;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class GenresMapper implements RowMapper<Genre> {
    @Override
    public Genre map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Genre(
                rs.getString("genre_id"),
                rs.getString("genre_name")
        );
    }
}
