package com.filmbooking.controller.client.film.auth;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        RenderViewUtils.renderViewToLayout(req, resp, ContextPathUtils.getClientPagesPath("book-film.jsp"), ContextPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmBookingServices = new FilmBookingServicesImpl(hibernateSessionProvider);
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        String seats = req.getParameter("seats");

        if (!seats.isEmpty()) {
            // clone film booking to allow user book film again if they want (user stay on the same page)
            // because save method we use persist() method of hibernate, so we need to clone film booking to avoid
            FilmBooking filmBookingClone = filmBooking.clone();
            bookedFilm = filmBookingClone.getShowtime().getFilm();
            filmBookingClone.setSeatsData(seats);
            filmBookingClone.setBookingDate(LocalDateTime.now());
            int numberOfSeats = filmBookingClone.getSeats().length;
            double totalFee = numberOfSeats * bookedFilm.getFilmPrice();
            filmBookingClone.setTotalFee(totalFee);

            if (filmBookingServices.save(filmBookingClone)) {
                Showtime bookedShowtime = filmBookingClone.getShowtime();
                bookedShowtime.bookSeats(filmBookingClone.getSeats());
                showtimeServices.update(bookedShowtime);
            }
            //reset film booking
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.BOOK_FILM_SUCCESSFUL.getStatusCode());
            doGet(req, resp);


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