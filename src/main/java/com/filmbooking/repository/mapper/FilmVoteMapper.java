package com.filmbooking.repository.mapper;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmVote;
import com.filmbooking.repository.FilmRepository;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FilmVoteMapper implements RowMapper<FilmVote> {
    @Override
    public FilmVote map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new FilmVote(
                rs.getLong("film_vote_id"),
                rs.getLong("film_id"),
                rs.getInt("scores"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }

}
