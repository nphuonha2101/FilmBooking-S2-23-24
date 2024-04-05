package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Theater;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.TheaterServicesImpl;
import com.filmbooking.utils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/v1/film-bookings/*", "/api/v1/film-bookings"})
public class FilmBookingAPI extends HttpServlet {
private FilmBookingServicesImpl filmBookingServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        filmBookingServices = new FilmBookingServicesImpl(sessionProvider);
        Gson gson = GSONUtils.getGson();
        String jsonResp = "";

        String id = req.getParameter("film-booking-id");
        if (id != null) {
            FilmBooking filmBooking = filmBookingServices.getByID(id);
            jsonResp = gson.toJson(filmBooking);
        } else {
            List<FilmBooking> filmBookingList = filmBookingServices.getAll().getMultipleResults();

            jsonResp = "[";

            for (FilmBooking filmBooking : filmBookingList) {
                jsonResp += gson.toJson(filmBooking);
                if (filmBookingList.indexOf(filmBooking) != filmBookingList.size() - 1) {
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
