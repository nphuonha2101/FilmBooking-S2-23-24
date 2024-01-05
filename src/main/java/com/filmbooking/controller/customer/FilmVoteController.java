package com.filmbooking.controller.customer;

/*
 *  @created 05/01/2024 - 10:36 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmVote;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IFilmVoteServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.FilmVoteServicesImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vote-film")
public class FilmVoteController extends HttpServlet {
    private IFilmVoteServices filmVoteServices;
    private IFilmServices filmServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);
        filmVoteServices = new FilmVoteServicesImpl(hibernateSessionProvider);

        String filmSlug = req.getParameter("film");
        int filmScores = Integer.parseInt(req.getParameter("scores"));

        Film film = filmServices.getBySlug(filmSlug);
        FilmVote filmVote = new FilmVote(film, filmScores);

        filmVoteServices.save(filmVote);
        resp.sendRedirect(req.getHeader("Referer"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmVoteServices = null;
        filmServices = null;
        hibernateSessionProvider = null;
    }
}
