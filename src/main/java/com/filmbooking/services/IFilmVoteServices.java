package com.filmbooking.services;

/*
 *  @created 05/01/2024 - 10:32 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmVote;

import java.util.List;

public interface IFilmVoteServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);

    boolean save(FilmVote filmVote);

    boolean checkIfFilmVoted(List<FilmVote> filmVotedList, FilmVote filmVote);
}
