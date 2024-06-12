package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.FilmVote;
import com.filmbooking.repository.mapper.FilmVoteMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
import java.util.Map;

public class FilmVoteRepository extends AbstractRepository<FilmVote> {

    public FilmVoteRepository(Class<FilmVote> modelClass) {
            super(modelClass);
    }

    public List<FilmVote> selectAllByFilmId(long filmId) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM film_votes WHERE film_id = :film_id";
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
    RowMapper<FilmVote> getRowMapper() {
        return new FilmVoteMapper();
    }

    @Override
    Map<String, Object> mapToRow(FilmVote filmVote) {
        return Map.of(
                "film_vote_id", filmVote.getId(),
                "film_id", filmVote.getFilm().getFilmID(),
                "scores", filmVote.getScores()
        );
    }


}
