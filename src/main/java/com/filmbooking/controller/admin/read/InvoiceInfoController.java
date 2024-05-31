package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.*;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.*;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/invoice-info")
public class InvoiceInfoController extends HttpServlet {
    private CRUDServicesLogProxy<FilmBooking> filmBookingServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmBookingServices = new CRUDServicesLogProxy<>(new FilmBookingServicesImpl(), req, FilmBooking.class);

        String bookingID = req.getParameter("booking-id");
        FilmBooking filmBooking = filmBookingServices.select(bookingID);

        Page invoiceInfoPage = new ClientPage(
                "invoiceInfoTitle",
                "invoice-info",
                "empty-layout"
        );
        invoiceInfoPage.putAttribute("bookedFilmBooking", filmBooking);
        ArrayList<String> customStyleSheets = new ArrayList<>();
        customStyleSheets.add("invoice-info");
        invoiceInfoPage.setCustomStyleSheets(customStyleSheets);

        invoiceInfoPage.putAttribute("bookedFilmBooking", filmBooking);
        invoiceInfoPage.render(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        hibernateSessionProvider = null;
    }
}
