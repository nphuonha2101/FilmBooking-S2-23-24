package com.filmbooking.controller.apis;

import com.filmbooking.controller.apis.apiResponse.APIJSONResponse;
import com.filmbooking.controller.apis.apiResponse.RespCodeEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.utils.APIUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/api/v1/film-bookings/*", "/api/v1/film-bookings"})
public class FilmBookingAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        FilmBookingServicesImpl filmBookingServices = new FilmBookingServicesImpl();

        String command = req.getParameter("command");
        String language = (String) req.getSession().getAttribute("lang");

        APIUtils<FilmBooking> apiUtils = new APIUtils<>(filmBookingServices, req, resp);

        if (command.equals("current")) {
            FilmBooking currentFilmBooking = (FilmBooking) req.getSession().getAttribute("filmBooking");
            System.out.println("currentFilmBooking: " + currentFilmBooking);
            APIJSONResponse apijsonResponse;

            if (currentFilmBooking == null)
                apijsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), language, null);
            else
                apijsonResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), language, currentFilmBooking);

            apiUtils.setJsonResponse(apijsonResponse);
            apiUtils.writeResponse(null, 0);
            return;
        }

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);

        sessionProvider.closeSession();
    }
}
