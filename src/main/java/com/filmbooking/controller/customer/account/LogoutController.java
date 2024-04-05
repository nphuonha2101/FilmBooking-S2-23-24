package com.filmbooking.controller.customer.account;

import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            FilmBooking filmBooking = (FilmBooking) session.getAttribute("filmBooking");
            Showtime showtime = filmBooking.getShowtime();

            // TODO: release booked seats
//            if (filmBooking.getBookedSeats() != null && showtime != null) {
//                System.out.println(filmBooking.getBookedSeats());
//                HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();
//                IShowtimeServices showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);
//                showtime.releaseSeats(filmBooking.getBookedSeats());
//                showtimeServices.update(showtime);
//                hibernateSessionProvider.closeSession();
//            }
            session.invalidate();
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/login"));
        } else resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));


    }
}
