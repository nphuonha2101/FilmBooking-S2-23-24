package com.filmbooking.filters;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/auth")
public class DeleteExpiredFilmBookingFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        FilmBooking filmBooking = (FilmBooking) session.getAttribute("filmBooking");
        Showtime showtime = filmBooking.getShowtime();

        if (showtime != null && filmBooking.getBookedSeats() != null && filmBooking.isExpired()) {
            System.out.println("Expired: " + filmBooking.isExpired());
            HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();
            IShowtimeServices showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

            showtime.releaseSeats(filmBooking.getBookedSeats());
            showtimeServices.update(showtime);

            hibernateSessionProvider.closeSession();
        }

        chain.doFilter(req, res);
    }
}
