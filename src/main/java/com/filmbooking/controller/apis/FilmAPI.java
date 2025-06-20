package com.filmbooking.controller.apis;

import com.filmbooking.model.Film;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.APIUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/v1/films/*", "/api/v1/films"})
public class FilmAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilmServicesImpl filmServices = new FilmServicesImpl();
        APIUtils<Film> apiUtils = new APIUtils<>(filmServices, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}
