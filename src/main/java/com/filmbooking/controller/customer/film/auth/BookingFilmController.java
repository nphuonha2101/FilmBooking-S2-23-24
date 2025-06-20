package com.filmbooking.controller.customer.film.auth;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@WebServlet(name = "bookFilm", value = "/auth/book-film")
public class BookingFilmController extends HttpServlet {
    private ShowtimeServicesImpl showtimeServices;
    private FilmBooking filmBooking;
    private Film bookedFilm;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeServices = new ShowtimeServicesImpl();
        CRUDServicesLogProxy<Showtime> showtimeServicesLog = new CRUDServicesLogProxy<>(showtimeServices, req, Showtime.class);

        // get film booking from session
        filmBooking = (FilmBooking) req.getSession(false).getAttribute("filmBooking");

        Showtime bookedShowtime = filmBooking.getShowtime();
        // update showtime from Database
        bookedShowtime = showtimeServicesLog.select(bookedShowtime.getIdValue());

        HashMap<Long, String[][]> showtimeIDAndSeatMatrix = showtimeServices.getShowtimeIDAndSeatMatrix();

        Page bookFilmPage = new ClientPage(
                "bookingFilmTitle",
                "book-film",
                "master"
        );

        bookFilmPage.putAttribute("bookedShowtime", bookedShowtime);
        bookFilmPage.putAttribute("showtimeIDAndSeatMatrix", showtimeIDAndSeatMatrix);

        bookFilmPage.render(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeServices = new ShowtimeServicesImpl();
        CRUDServicesLogProxy<Showtime> showtimeServicesLog = new CRUDServicesLogProxy<>(new ShowtimeServicesImpl(), req, Showtime.class);

        String seats = req.getParameter("seats");

        if (!seats.isEmpty()) {
            bookedFilm = filmBooking.getShowtime().getFilm();
            filmBooking.setSeatsData(seats);
            filmBooking.setBookingDate(LocalDateTime.now());
            int numberOfSeats = filmBooking.getBookedSeats().length;
            double totalFee = numberOfSeats * bookedFilm.getFilmPrice();
            filmBooking.setTotalFee(totalFee);

            HttpSession session = req.getSession(false);
            session.setAttribute("filmBooking", filmBooking);

            Showtime showtime = filmBooking.getShowtime();
            // update showtime from database
            showtime = showtimeServicesLog.select(showtime.getIdValue());

            showtime.reserveSeats(seats.split(", "));

            System.out.println("Showtime reserved seats: " + showtime.getSeatsData());

            showtimeServicesLog.update(showtime);

            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/auth/checkout"));

        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.PLS_CHOOSE_SEAT.getStatusCode());
            doGet(req, resp);
        }
    }

    @Override
    public void destroy() {
        showtimeServices = null;
        filmBooking = null;
        bookedFilm = null;
    }


}
