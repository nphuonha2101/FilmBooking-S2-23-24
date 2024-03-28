package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Theater;
import com.filmbooking.services.impls.TheaterServicesImpl;
import com.filmbooking.utils.GSONUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        theaterServices = new TheaterServicesImpl(sessionProvider);
        Gson gson = GSONUtils.getGson();

        List<Theater> theaterList = theaterServices.getAll().getMultipleResults();

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
