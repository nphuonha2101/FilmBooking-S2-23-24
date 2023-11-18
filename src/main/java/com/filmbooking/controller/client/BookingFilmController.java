package com.filmbooking.controller.client;

import com.filmbooking.model.*;
import com.filmbooking.services.*;
import com.filmbooking.services.impls.*;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
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
    private IFilmServices filmServices;
    private IShowtimeServices showtimeServices;
    private IRoomServices roomServices;
    private ITheaterServices theaterServices;
    private FilmBooking filmBooking;
    private Film bookedFilm;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
        roomServices = new RoomServicesImpl();
        theaterServices = new TheaterServicesImpl();

        // get showtime id from session
        filmBooking = (FilmBooking) req.getSession(false).getAttribute("filmBooking");
        String showtimeID = filmBooking.getShowtimeID();


        Showtime bookedShowtime = showtimeServices.getByID(showtimeID);
        String filmID = bookedShowtime.getFilmID();
        bookedFilm = filmServices.getByFilmID(filmID);
        Room bookedRoom = roomServices.getByRoomID(bookedShowtime.getRoomID());
        Theater bookedTheater = theaterServices.getByID(bookedRoom.getTheaterID());

        System.out.println("filmID: " + filmID + "\n" + "showtimeID: " + showtimeID);

        req.setAttribute("bookedFilm", bookedFilm);
        req.setAttribute("bookedShowtime", bookedShowtime);
        req.setAttribute("bookedRoom", bookedRoom);
        req.setAttribute("bookedTheater", bookedTheater);

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

        filmBooking.setSeatsData(seats);
        filmBooking.setBookingDate(LocalDateTime.now());
        int numberOfSeats = filmBooking.getSeats().length;
        double totalFee = numberOfSeats * bookedFilm.getFilmPrice();
        filmBooking.setTotalFee(totalFee);

        filmBookingServices.save(filmBooking);
        //reset film booking
        filmBooking.resetFilmBooking();

        resp.sendRedirect("home");

    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        filmServices = null;
        theaterServices = null;
        showtimeServices = null;
        roomServices = null;
        filmBooking = null;
        bookedFilm = null;
    }
}
