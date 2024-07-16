package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmVote;
import com.filmbooking.repository.mapper.FilmVoteMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmVoteRepository extends AbstractRepository<FilmVote> {

    public FilmVoteRepository() {
            super(FilmVote.class);
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


    public boolean deleteByFilmId(long filmId) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "DELETE FROM film_votes WHERE film_id = :film_id";
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

            List<FilmVote> filmVotes = film.getFilmVoteList();

            if (filmVotes == null) {
                return true;
            }

            String sql = "INSERT INTO film_votes (film_id, scores) VALUES (:film_id, :scores)";
            Handle handle = JdbiDBConnection.openHandle();


            for (FilmVote filmVote : filmVotes) {
                boolean updateResult = handle.createUpdate(sql)
                        .bind("film_id", filmVote.getFilm().getFilmID())
                        .bind("scores", filmVote.getScores())
                        .execute() == 1;

                if (!updateResult) {
                    return false;

                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
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
        Map<String, Object> result = new HashMap<>();
        result.put("film_id", filmVote.getFilm().getFilmID());
        result.put("scores", filmVote.getScores());
        result.put("created_at", filmVote.getCreatedAt());
        result.put("updated_at", filmVote.getUpdatedAt());

        return result;
    }


}
