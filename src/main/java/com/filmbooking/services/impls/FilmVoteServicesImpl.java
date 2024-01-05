package com.filmbooking.services.impls;

/*
 *  @created 05/01/2024 - 10:33 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.dao.GenericDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmVote;
import com.filmbooking.services.IFilmVoteServices;

import java.util.List;

public class FilmVoteServicesImpl implements IFilmVoteServices {
    private final IDAO<FilmVote> filmVoteDAO;

    public FilmVoteServicesImpl(HibernateSessionProvider sessionProvider) {
        this.filmVoteDAO = new GenericDAOImpl<>(FilmVote.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        filmVoteDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public boolean save(FilmVote filmVote) {
        return filmVoteDAO.save(filmVote);
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
