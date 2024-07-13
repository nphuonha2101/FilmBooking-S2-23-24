package com.filmbooking.filters;

import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
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

            ShowtimeServicesImpl showtimeServices = new ShowtimeServicesImpl();

            if (showtime.releaseSeats(filmBooking.getBookedSeats()))
                filmBooking.setSeatsData(showtime.getSeatsData());

            showtimeServices.update(showtime);

            FilmBooking newFilmBooking = new FilmBooking();
            newFilmBooking.setUser(filmBooking.getUser());
            session.setAttribute("filmBooking", newFilmBooking);
        }

        chain.doFilter(req, res);
    }
}
