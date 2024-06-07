package com.filmbooking.controller.customer;

/*
 *  @created 05/01/2024 - 10:36 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmVote;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.FilmVoteServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vote-film")
public class FilmVoteController extends HttpServlet {
    private FilmVoteServicesImpl filmVoteServices;
    private FilmServicesImpl filmServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();
        filmVoteServices = new FilmVoteServicesImpl();

        String filmSlug = req.getParameter("film");
        int filmScores = Integer.parseInt(req.getParameter("scores"));

        Film film = filmServices.getBySlug(filmSlug);
        FilmVote filmVote = new FilmVote(film, filmScores);

        filmVoteServices.insert(filmVote);
        resp.sendRedirect(req.getHeader("Referer"));

    }

    @Override
    public void destroy() {
        filmVoteServices = null;
        filmServices = null;
    }
}
