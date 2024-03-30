package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.*;
import com.filmbooking.services.impls.*;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/invoice-info")
public class InvoiceInfoController extends HttpServlet {
    private CRUDServicesLogProxy<FilmBooking> filmBookingServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmBookingServices = new CRUDServicesLogProxy<>(new FilmBookingServicesImpl(), req, hibernateSessionProvider);

        String bookingID = req.getParameter("booking-id");

        System.out.println("Booking ID: " + bookingID);

        FilmBooking filmBooking = filmBookingServices.getByID(bookingID);

        System.out.println(filmBooking);

        req.setAttribute("bookedFilmBooking", filmBooking);

        req.setAttribute("pageTitle", "invoiceInfoTitle");
        req.getRequestDispatcher(WebAppPathUtils.getClientPagesPath("invoice-info.jsp")).forward(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        hibernateSessionProvider = null;
    }
}
