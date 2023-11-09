package com.filmbooking.controller.client;

import com.filmbooking.model.Showtime;
import com.filmbooking.model.User;
import com.filmbooking.services.*;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.services.impls.ShowtimeViewServicesImpl;
import com.filmbooking.services.impls.TheaterServicesImpl;
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
    private IShowtimeViewServices showtimeViewServices;
    private ITheaterServices theaterServices;
    private IShowtimeServices showtimeServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmBookingServices = new FilmBookingServicesImpl();
        showtimeViewServices = new ShowtimeViewServicesImpl();
        theaterServices = new TheaterServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();

        req.setAttribute("sectionTitle", "FilmBooking - Lịch sử đặt phim");
        req.setAttribute("sectionTitle", "Lịch sử đặt phim");


        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser != null)
            if (loginUser.getAccountRole().equalsIgnoreCase("admin"))
                req.setAttribute("filmBookings", filmBookingServices.getAll());
            else
                req.setAttribute("filmBookings", filmBookingServices.getAllByUsername(loginUser.getUsername()));

        req.setAttribute("showtimeViewsMap", showtimeViewServices.getShowtimeViewAndShowtimeID());
        req.setAttribute("theatersMap", theaterServices.getTheaterAndTheaterID());
        req.setAttribute("showtimesMap", showtimeServices.getShowtimeAndShowtimeID());

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("booking-history.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        showtimeViewServices = null;
        theaterServices = null;
        showtimeServices = null;
    }
}
