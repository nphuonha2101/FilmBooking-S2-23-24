package com.filmbooking.controller.client;

import com.filmbooking.model.*;
import com.filmbooking.model.view.ShowtimeView;
import com.filmbooking.services.*;
import com.filmbooking.services.impls.*;
import com.filmbooking.utils.ContextPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/invoice-info")
public class InvoiceInfoController extends HttpServlet {
    private IFilmBookingServices filmBookingServices;
    private IFilmServices filmServices;
    private IShowtimeServices showtimeServices;
    private ITheaterServices theaterServices;
    private IRoomServices roomServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmBookingServices = new FilmBookingServicesImpl();
        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
        roomServices = new RoomServicesImpl();
        theaterServices = new TheaterServicesImpl();

        String bookingID = req.getParameter("booking-id");

        System.out.println("Booking ID: " + bookingID);

        FilmBooking filmBooking = filmBookingServices.getByFilmBookingID(bookingID);
        System.out.println(filmBooking);
        System.out.println(Arrays.toString(filmBooking.getSeats()));
        Showtime bookedShowtime = showtimeServices.getByID(filmBooking.getShowtimeID());
        Room bookedRoom = roomServices.getByRoomID(bookedShowtime.getRoomID());
        Film bookedFilm = filmServices.getByFilmID(bookedShowtime.getFilmID());
        Theater bookedTheater = theaterServices.getByID(bookedRoom.getTheaterID());


        req.setAttribute("bookedFilmBooking", filmBooking);
        req.setAttribute("bookedFilm", bookedFilm);
        req.setAttribute("bookedShowtime", bookedShowtime);
        req.setAttribute("bookedRoom", bookedRoom);
        req.setAttribute("bookedTheater", bookedTheater);

        req.setAttribute("pageTitle", "invoiceInfoTitle");
        req.getRequestDispatcher(ContextPathUtils.getClientPagesPath("invoice-info.jsp")).forward(req, resp);

    }

    @Override
    public void destroy() {
        filmServices = null;
        filmBookingServices = null;
        showtimeServices = null;
        theaterServices = null;
        roomServices = null;
    }
}
