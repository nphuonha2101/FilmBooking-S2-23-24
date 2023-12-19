package com.filmbooking.controller.client.film.auth;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.services.*;
import com.filmbooking.services.impls.*;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/booking-history")
public class BookingHistoryController extends HttpServlet {
    private IFilmBookingServices filmBookingServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmBookingServices = new FilmBookingServicesImpl(hibernateSessionProvider);

        req.setAttribute("pageTitle", "bookingHistoryTitle");

        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser != null)
            if (loginUser.getAccountRole().equalsIgnoreCase("admin"))
                req.setAttribute("filmBookings", filmBookingServices.getAll());
            else
                req.setAttribute("filmBookings", filmBookingServices.getAllByUser(loginUser));

        RenderViewUtils.renderViewToLayout(req, resp,
                PathUtils.getClientPagesPath("booking-history.jsp"),
                PathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        hibernateSessionProvider = null;
    }
}
