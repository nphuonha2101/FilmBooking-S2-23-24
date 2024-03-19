package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmVote;
import com.filmbooking.model.Theater;
import com.filmbooking.services.IFilmVoteServices;
import com.filmbooking.services.ITheaterServices;
import com.filmbooking.services.impls.FilmVoteServicesImpl;
import com.filmbooking.services.impls.TheaterServicesImpl;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/v1/film-votes/*", "/api/v1/film-votes"})
public class FilmVotesAPI extends HttpServlet {
    private ITheaterServices theaterServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        theaterServices = new TheaterServicesImpl(sessionProvider);
        Gson gson = new Gson();

        List<Theater> theaterList = theaterServices.getAll();

        String jsonResp = "[";

        for (Theater theater: theaterList) {
            jsonResp += gson.toJson(theater);

            if (theaterList.indexOf(theater) != theaterList.size() - 1) {
                jsonResp += ",";
            }
        }

        jsonResp += "]";

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);
    }
}
