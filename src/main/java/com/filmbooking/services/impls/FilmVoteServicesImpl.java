package com.filmbooking.services.impls;

import java.util.Objects;

/*
 *  @created 05/01/2024 - 10:33 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmVote;
import com.filmbooking.model.User;
import com.filmbooking.repository.AbstractRepository;
import com.filmbooking.repository.FilmVoteRepository;
import com.filmbooking.services.AbstractService;


public class FilmVoteServicesImpl extends AbstractService<FilmVote> {
    public FilmVoteServicesImpl() {
        super(new FilmVoteRepository(FilmVote.class));
    }


//    public FilmVoteServicesImpl(HibernateSessionProvider sessionProvider) {
//        this.decoratedDAO = new DataAccessObjects<>(FilmVote.class);
//        this.setSessionProvider(sessionProvider);
//    }
//
//    public FilmVoteServicesImpl() {
//        this.decoratedDAO = new DataAccessObjects<>(FilmVote.class);
//    }
//
//    @Override
//    public String getTableName() {
//        return FilmVote.TABLE_NAME;
//    }
//
//    @Override
//    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
//        this.decoratedDAO.setSessionProvider(sessionProvider);
//    }
//
//    @Override
//    public FilmVote getBySlug(String slug) {
//        throw new UnsupportedOperationException("This method is not supported for FilmVote");
//    }
//
//    @Override
//    public FilmVote getByID(String id) {
//        if (!Objects.equals(id, "null"))
//            return this.decoratedDAO.getByID(id, true);
//        else
//            throw new RuntimeException("ID must not be null");
//    }
//
//    @Override
//    public boolean update(FilmVote filmVote) {
//        throw new UnsupportedOperationException("This method is not supported for FilmVote");
//    }
//
//    @Override
//    public boolean delete(FilmVote filmVote) {
//        throw new UnsupportedOperationException("This method is not supported for FilmVote");
//    }
}
