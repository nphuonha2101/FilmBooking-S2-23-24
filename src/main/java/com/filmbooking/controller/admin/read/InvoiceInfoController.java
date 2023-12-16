package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
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

@WebServlet("/admin/invoice-info")
public class InvoiceInfoController extends HttpServlet {
    private IFilmBookingServices filmBookingServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmBookingServices = new FilmBookingServicesImpl(hibernateSessionProvider);

        String bookingID = req.getParameter("booking-id");

        System.out.println("Booking ID: " + bookingID);

        FilmBooking filmBooking = filmBookingServices.getByFilmBookingID(bookingID);

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

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        hibernateSessionProvider = null;
    }
}