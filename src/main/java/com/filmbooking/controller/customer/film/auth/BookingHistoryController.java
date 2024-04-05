package com.filmbooking.controller.customer.film.auth;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.*;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/booking-history")
public class BookingHistoryController extends HttpServlet {
    private FilmBookingServicesImpl filmBookingServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmBookingServices = new FilmBookingServicesImpl(hibernateSessionProvider);

        req.setAttribute("pageTitle", "bookingHistoryTitle");

        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser != null)
            if (loginUser.getAccountRole().equalsIgnoreCase("admin"))
                req.setAttribute("filmBookings", filmBookingServices.getAll().getMultipleResults());
            else
                req.setAttribute("filmBookings", filmBookingServices.getAllByUser(loginUser));

        RenderViewUtils.renderViewToLayout(req, resp,
                WebAppPathUtils.getClientPagesPath("booking-history.jsp"),
                WebAppPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        hibernateSessionProvider = null;
    }
}
