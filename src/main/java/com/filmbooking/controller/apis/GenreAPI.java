package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Genre;
import com.filmbooking.services.impls.GenreServicesImpl;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/v1/genres/*", "/api/v1/genres"})
public class GenreAPI extends HttpServlet {
    private GenreServicesImpl genreServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        genreServices = new GenreServicesImpl(sessionProvider);
        Gson gson = GSONUtils.getGson();
        String jsonResp = "";

        String id = req.getParameter("genre-id");
        if (id != null) {
            Genre genre = genreServices.getByID(id);
            jsonResp = gson.toJson(genre);
        } else {
            List<Genre> genreList = genreServices.getAll().getMultipleResults();

            jsonResp = "[";

            for (Genre genre : genreList) {
                jsonResp += gson.toJson(genre);
                if (genreList.indexOf(genre) != genreList.size() - 1) {
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
