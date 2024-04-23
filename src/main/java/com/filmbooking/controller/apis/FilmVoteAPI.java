package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmVote;
import com.filmbooking.services.impls.FilmVoteServicesImpl;
import com.filmbooking.utils.APIUtils;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/v1/film-vote/*", "/api/v1/film-vote"})
public class FilmVoteAPI extends HttpServlet {
    private FilmVoteServicesImpl filmVoteServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        filmVoteServices = new FilmVoteServicesImpl(sessionProvider);

        APIUtils<FilmVote> apiUtils = new APIUtils<>(filmVoteServices, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}
