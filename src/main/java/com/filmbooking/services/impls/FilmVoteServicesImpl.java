package com.filmbooking.services.impls;

import java.util.Objects;

/*
 *  @created 05/01/2024 - 10:33 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */


import com.filmbooking.model.FilmVote;
import com.filmbooking.repository.FilmVoteRepository;
import com.filmbooking.services.AbstractService;


public class FilmVoteServicesImpl extends AbstractService<FilmVote> {
    public FilmVoteServicesImpl() {
        super(new FilmVoteRepository(FilmVote.class));
    }

    @Override
    public FilmVote getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for FilmVote");
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
