package com.filmbooking.services.impls;

/*
 *  @created 05/01/2024 - 10:33 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmVote;
import com.filmbooking.services.IFilmVoteServices;

import java.util.List;

public class FilmVoteServicesImpl implements IFilmVoteServices {
    private final DataAccessObjects<FilmVote> filmVoteDataAccessObjects;

    public FilmVoteServicesImpl(HibernateSessionProvider sessionProvider) {
        this.filmVoteDataAccessObjects = new DataAccessObjects<>(FilmVote.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        filmVoteDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public boolean save(FilmVote filmVote) {
        return filmVoteDataAccessObjects.save(filmVote);
    }

    @Override
    public boolean checkIfFilmVoted(List<FilmVote> filmVotedList, FilmVote filmVote) {
        for (FilmVote filmVoteInList: filmVotedList) {
            if (filmVoteInList.getFilm().getFilmID() == filmVote.getId())
                return true;
        }
        return false;
    }
}
