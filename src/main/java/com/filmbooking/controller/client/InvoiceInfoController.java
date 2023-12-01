package com.filmbooking.controller.client;

import com.filmbooking.model.*;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmBookingServices = new FilmBookingServicesImpl();
        filmBookingServices.openSession();

        String bookingID = req.getParameter("booking-id");

        System.out.println("Booking ID: " + bookingID);

        FilmBooking filmBooking = filmBookingServices.getByFilmBookingID(bookingID);
        System.out.println(filmBooking);
        System.out.println(Arrays.toString(filmBooking.getSeats()));
        Showtime bookedShowtime = filmBooking.getShowtime();

        Room bookedRoom = bookedShowtime.getRoom();
        Film bookedFilm = bookedShowtime.getFilm();
        Theater bookedTheater = bookedRoom.getTheater();


        req.setAttribute("bookedFilmBooking", filmBooking);
        req.setAttribute("bookedFilm", bookedFilm);
        req.setAttribute("bookedShowtime", bookedShowtime);
        req.setAttribute("bookedRoom", bookedRoom);
        req.setAttribute("bookedTheater", bookedTheater);

        req.setAttribute("pageTitle", "invoiceInfoTitle");
        req.getRequestDispatcher(ContextPathUtils.getClientPagesPath("invoice-info.jsp")).forward(req, resp);

        filmBookingServices.closeSession();
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
    }
}
