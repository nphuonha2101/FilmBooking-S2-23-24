package com.filmbooking.controller.apis;

import com.filmbooking.controller.apis.apiResponse.APIJSONResponse;
import com.filmbooking.controller.apis.apiResponse.RespCodeEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.services.impls.FilmServicesImpl;
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

@WebServlet(urlPatterns = {"/api/v1/films/*", "/api/v1/films"})
public class FilmAPI extends HttpServlet {
    private FilmServicesImpl filmServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(sessionProvider);
        APIUtils<Film> apiUtils = new APIUtils<>(filmServices, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);

        sessionProvider.closeSession();
    }
}
