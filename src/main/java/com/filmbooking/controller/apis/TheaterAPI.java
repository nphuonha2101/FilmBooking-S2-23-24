package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Theater;
import com.filmbooking.services.impls.TheaterServicesImpl;
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

@WebServlet(urlPatterns = {"/api/v1/theaters/*", "/api/v1/theaters"})
public class TheaterAPI extends HttpServlet {
    private TheaterServicesImpl theaterServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        theaterServices = new TheaterServicesImpl();

        APIUtils<Theater> apiUtils = new APIUtils<>(theaterServices, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}