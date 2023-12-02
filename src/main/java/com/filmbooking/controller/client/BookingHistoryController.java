package com.filmbooking.controller.client;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.User;
import com.filmbooking.services.*;
import com.filmbooking.services.impls.*;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/booking-history")
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
                ContextPathUtils.getClientPagesPath("booking-history.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        hibernateSessionProvider = null;
    }
}
