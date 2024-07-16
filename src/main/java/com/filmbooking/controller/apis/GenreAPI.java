package com.filmbooking.controller.apis;

import com.filmbooking.model.Genre;
import com.filmbooking.services.impls.GenreServicesImpl;
import com.filmbooking.utils.APIUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/api/v1/genres/*", "/api/v1/genres"})
public class GenreAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenreServicesImpl genreServices = new GenreServicesImpl();

        APIUtils<Genre> apiUtils = new APIUtils<>(genreServices, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}
