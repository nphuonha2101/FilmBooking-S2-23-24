package com.filmbooking.repository.mapper;

import com.filmbooking.model.*;
import com.filmbooking.repository.FilmVoteRepository;
import com.filmbooking.repository.GenreRepository;
import com.filmbooking.repository.ShowtimeRepository;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper implements RowMapper<Film> {
    @Override
    public Film map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Film(
                rs.getLong("film_id"),
                rs.getString("film_name"),
                rs.getDouble("film_price"),
                rs.getString("film_director"),
                rs.getString("film_cast"),
                rs.getInt("film_length"),
                rs.getString("film_description"),
                rs.getString("film_trailer_link"),
                rs.getString("img_path"),
                rs.getString("slug"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
