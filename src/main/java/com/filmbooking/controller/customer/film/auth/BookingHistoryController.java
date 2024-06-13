package com.filmbooking.controller.customer.film.auth;

import com.filmbooking.model.User;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/booking-history")
public class BookingHistoryController extends HttpServlet {
    private FilmBookingServicesImpl filmBookingServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmBookingServices = new FilmBookingServicesImpl();

        Page bookingHistoryPage = new ClientPage(
                "bookingHistoryTitle",
                "booking-history",
                "master"
        );

        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser != null)
            if (loginUser.getAccountRole().equalsIgnoreCase("admin"))
                bookingHistoryPage.putAttribute("filmBookings", filmBookingServices.selectAll());
            else
                bookingHistoryPage.putAttribute("filmBookings", filmBookingServices.selectAllByUser(loginUser));

        bookingHistoryPage.render(req, resp);

    }

    @Override
    public void destroy() {
        filmBookingServices = null;
    }
}
