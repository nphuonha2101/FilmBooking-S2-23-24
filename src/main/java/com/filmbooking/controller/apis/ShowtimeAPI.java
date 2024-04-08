package com.filmbooking.controller.apis;

import java.io.IOException;
import java.util.List;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/v1/showtimes/*", "/api/v1/showtimes" })
public class ShowtimeAPI extends HttpServlet {
    ShowtimeServicesImpl showtimeServicesImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        showtimeServicesImpl = new ShowtimeServicesImpl(sessionProvider);
        Gson gson = GSONUtils.getGson();
        String jsonResp = "";
        String id = req.getParameter("showtime-id");
        if (id != null) {
            Showtime showtime = showtimeServicesImpl.getByID(id);
            jsonResp += gson.toJson(showtime);
        } else {
            List<Showtime> showtimesList = showtimeServicesImpl.getAll().getMultipleResults();
            jsonResp += "[";
            for (Showtime showtime : showtimesList) {
                jsonResp += gson.toJson(showtime);
                if (showtimesList.indexOf(showtime) != showtimesList.size() - 1) {
                    jsonResp += ",";
                }
            }
            jsonResp += "]";
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonResp);
    }
}
