package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Theater;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/v1/films/*", "/api/v1/films"})
public class FilmAPI extends HttpServlet {
    private FilmServicesImpl filmServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(sessionProvider);
        Gson gson = GSONUtils.getGson();
        String jsonResp = "";
        String id = req.getParameter("film-id");
        if (id != null) {
            Film film = filmServices.getByID(id);
            jsonResp = gson.toJson(film);
        } else {
            List<Film> filmList = filmServices.getAll().getMultipleResults();

            jsonResp = "[";

            for (Film film : filmList) {
                jsonResp += gson.toJson(film);
                if (filmList.indexOf(film) != filmList.size() - 1) {
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
