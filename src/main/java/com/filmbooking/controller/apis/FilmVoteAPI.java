package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.FilmVote;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.FilmVoteServicesImpl;
import com.filmbooking.utils.GSONUtils;
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
        Gson gson = GSONUtils.getGson();
        String jsonResp = "";

        String id = req.getParameter("film-vote-id");
        if (id != null) {
            FilmVote filmVote = filmVoteServices.getByID(id);
            jsonResp = gson.toJson(filmVote);
        } else {
            List<FilmVote> filmVoteList = filmVoteServices.getAll().getMultipleResults();

            jsonResp = "[";

            for (FilmVote filmVote : filmVoteList) {
                jsonResp += gson.toJson(filmVote);
                if (filmVoteList.indexOf(filmVote) != filmVoteList.size() - 1) {
                    jsonResp += ",";
                }
            }
            jsonResp += "]";
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);
    }
}
