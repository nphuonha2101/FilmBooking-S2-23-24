package com.filmbooking.controller.customer.film.auth;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
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
    private IFilmBookingServices filmBookingServices;
    private IShowtimeServices showtimeServices;
    private FilmBooking filmBooking;
    private Film bookedFilm;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        // get film booking from session
        filmBooking = (FilmBooking) req.getSession(false).getAttribute("filmBooking");

        Showtime bookedShowtime = filmBooking.getShowtime();
        HashMap<Long, String[][]> showtimeIDAndSeatMatrix = showtimeServices.getShowtimeIDAndSeatMatrix();

        System.out.println("showtimeIDAndSeatMatrix = " + showtimeIDAndSeatMatrix);

        req.setAttribute("bookedShowtime", bookedShowtime);
        req.setAttribute("showtimeIDAndSeatMatrix", showtimeIDAndSeatMatrix);

        req.setAttribute("pageTitle", "bookingFilmTitle");

        RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getClientPagesPath("book-film.jsp"), PathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmBookingServices = new FilmBookingServicesImpl(hibernateSessionProvider);
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);


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
            showtime.reserveSeats(seats.split(", "));

            showtimeServices.update(showtime);

            resp.sendRedirect(PathUtils.getURLWithContextPath(req, resp,  "/auth/checkout"));

//            if (filmBookingServices.save(filmBookingClone)) {
//                Showtime bookedShowtime = filmBookingClone.getShowtime();
//                // if seats have not booked!
//                if (bookedShowtime.bookSeats(filmBookingClone.getSeats())) {
//                    showtimeServices.update(bookedShowtime);
//                    req.setAttribute("statusCodeSuccess", StatusCodeEnum.BOOKING_FILM_SUCCESSFUL.getStatusCode());
//                } else {
//                    req.setAttribute("statusCodeErr", StatusCodeEnum.SEATS_HAVE_ALREADY_BOOKED.getStatusCode());
//                }
//            } else {
//                req.setAttribute("statusCodeErr", StatusCodeEnum.BOOKING_FILM_FAILED.getStatusCode());
//            }
//            doGet(req, resp);

        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.PLS_CHOOSE_SEAT.getStatusCode());
            doGet(req, resp);
        }
        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        showtimeServices = null;
        filmBooking = null;
        bookedFilm = null;
        hibernateSessionProvider = null;
    }


}
