package com.filmbooking.controller.apis;

import com.filmbooking.controller.apis.apiResponse.APIJSONResponse;
import com.filmbooking.controller.apis.apiResponse.RespCodeEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
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
import java.util.Map;

@WebServlet(urlPatterns = {"/api/v1/film-bookings/*", "/api/v1/film-bookings"})
public class FilmBookingAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        FilmBookingServicesImpl filmBookingServices = new FilmBookingServicesImpl(sessionProvider);

        String id = req.getParameter("film-booking-id");
        boolean isCurrentFilmBooking = req.getParameter("current") != null;
        int offset = 0;
        int limit = 0;
        offset = req.getParameter("offset") != null ? Integer.parseInt(req.getParameter("offset")) : 0;
        limit = req.getParameter("limit") != null ? Integer.parseInt(req.getParameter("limit")) : 0;

        String jsonResp = "";
        String currentLanguage = (String) req.getSession(false).getAttribute("lang");
            APIUtils<FilmBooking> apiUtils = new APIUtils<>(filmBookingServices, req);

        if (id == null && !isCurrentFilmBooking) {
            jsonResp = apiUtils.getAll();
        }

        if (id != null) {
            FilmBooking filmBooking = filmBookingServices.getByID(id);
            Gson gson = GSONUtils.getGson();
            jsonResp = gson.toJson(filmBooking);
        }

        if (isCurrentFilmBooking) {
            FilmBooking currentFilmBooking = (FilmBooking) req.getSession().getAttribute("filmBooking");
            System.out.println("currentFilmBooking: " + currentFilmBooking);
            APIJSONResponse apijsonResponse;

            if (currentFilmBooking == null)
                apijsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
            else
                apijsonResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, currentFilmBooking);

            jsonResp = apijsonResponse.getResponse();
        }

        if (offset >= 0 && limit != 0) {
            jsonResp = apiUtils.getByOffset(offset, limit);
        }

        APIUtils.writeResponse(resp, jsonResp, null, 0);

        sessionProvider.closeSession();
    }
}
