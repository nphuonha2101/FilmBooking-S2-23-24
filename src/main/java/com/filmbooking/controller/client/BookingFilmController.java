package com.filmbooking.controller.client;

import com.filmbooking.model.*;
import com.filmbooking.services.*;
import com.filmbooking.services.impls.*;
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

@WebServlet(name = "bookFilm", value = "/book-film")
public class BookingFilmController extends HttpServlet {
    private IFilmBookingServices filmBookingServices;
    private IShowtimeServices showtimeServices;
    private FilmBooking filmBooking;
    private Film bookedFilm;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeServices = new ShowtimeServicesImpl();

        // get showtime id from session
        filmBooking = (FilmBooking) req.getSession(false).getAttribute("filmBooking");
        String showtimeID = String.valueOf(filmBooking.getShowtime().getShowtimeID());


        Showtime bookedShowtime = showtimeServices.getByID(showtimeID);

        bookedFilm = bookedShowtime.getFilm();

        Room bookedRoom = bookedShowtime.getRoom();


        req.setAttribute("bookedFilm", bookedFilm);
        req.setAttribute("bookedShowtime", bookedShowtime);
        req.setAttribute("bookedRoom", bookedRoom);

        req.setAttribute("pageTitle", "bookingFilmTitle");
        req.setAttribute("sectionTitle", "Đặt vé");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("book-film.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmBookingServices = new FilmBookingServicesImpl();
        String seats = req.getParameter("seats");

        if (!seats.isEmpty()) {

            filmBooking.setSeatsData(seats);
            filmBooking.setBookingDate(LocalDateTime.now());
            int numberOfSeats = filmBooking.getSeats().length;
            double totalFee = numberOfSeats * bookedFilm.getFilmPrice();
            filmBooking.setTotalFee(totalFee);

            filmBookingServices.save(filmBooking);
            //reset film booking
            filmBooking.resetFilmBooking();

            resp.sendRedirect("home");
        } else {
            req.setAttribute("pageTitle", "bookingFilmTitle");
            req.setAttribute("statusCodeErr", StatusCodeEnum.PLS_CHOOSE_SEAT.getStatusCode());

            resp.sendRedirect("book-film");

        }

    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        showtimeServices = null;
        filmBooking = null;
        bookedFilm = null;
    }
}
