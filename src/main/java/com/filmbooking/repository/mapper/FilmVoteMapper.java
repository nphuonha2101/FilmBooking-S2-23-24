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
    private FilmRepository filmRepository;
    @Override
    public FilmVote map(ResultSet rs, StatementContext ctx) throws SQLException {
        filmRepository = new FilmRepository(Film.class);
        return new FilmVote(
                rs.getLong("film_vote_id"),
                filmRepository.select(rs.getLong("film_id")),
                rs.getInt("scores")
        );
    }

}
