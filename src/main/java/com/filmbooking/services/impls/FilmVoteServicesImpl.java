package com.filmbooking.services.impls;

/*
 *  @created 05/01/2024 - 10:33 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmVote;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.IServices;

import java.util.List;
import java.util.Map;

public class FilmVoteServicesImpl extends AbstractServices<FilmVote> {
    private final DataAccessObjects<FilmVote> filmVoteDataAccessObjects;

    public FilmVoteServicesImpl(HibernateSessionProvider sessionProvider) {
        this.filmVoteDataAccessObjects = new DataAccessObjects<>(FilmVote.class);
        super.setSessionProvider(sessionProvider);
    }

    @Override
    public FilmVote getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for FilmVote");
    }

    @Override
    public FilmVote getByID(String id) {
        return this.filmVoteDataAccessObjects.getByID(id, true);
    }

    @Override
    public boolean update(FilmVote filmVote) {
     throw new UnsupportedOperationException("This method is not supported for FilmVote");
    }

    @Override
    public boolean delete(FilmVote filmVote) {
     throw new UnsupportedOperationException("This method is not supported for FilmVote");
    }

}
