package com.filmbooking.repository;

import com.filmbooking.model.FilmVote;
import com.filmbooking.repository.mapper.FilmVoteMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class FilmVoteRepository extends AbstractRepository<FilmVote> {

        public FilmVoteRepository(Class<FilmVote> modelClass) {
            super(modelClass);
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
