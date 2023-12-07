package com.filmbooking.controller.client.film;

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

@WebServlet(name = "bookFilm", value = "/book-film")
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
        req.setAttribute("sectionTitle", "Đặt vé");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("book-film.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

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
            int numberOfSeats = filmBooking.getSeats().length;
            double totalFee = numberOfSeats * bookedFilm.getFilmPrice();
            filmBooking.setTotalFee(totalFee);

            Showtime bookedShowtime = filmBooking.getShowtime();
            bookedShowtime.bookSeats(filmBooking.getSeats());

            showtimeServices.update(bookedShowtime);
            filmBookingServices.save(filmBooking);
            //reset film booking
            filmBooking.resetFilmBooking();

            resp.sendRedirect("home");
        } else {
            // req.setAttribute("pageTitle", "bookingFilmTitle");
            // req.setAttribute("statusCodeErr", StatusCodeEnum.PLS_CHOOSE_SEAT.getStatusCode());

            //resp.sendRedirect("book-film");
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
