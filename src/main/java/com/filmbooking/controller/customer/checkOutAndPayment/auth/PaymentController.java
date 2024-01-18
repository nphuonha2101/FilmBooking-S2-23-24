package com.filmbooking.controller.customer.checkOutAndPayment.auth;

/*
 *  @created 13/01/2024 - 9:05 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/auth/payment")
public class PaymentController extends HttpServlet {
    private HibernateSessionProvider hibernateSessionProvider;
    private IFilmBookingServices filmBookingServices;
    private IShowtimeServices showtimeServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmBookingServices = new FilmBookingServicesImpl(hibernateSessionProvider);
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        String vnPayRespCode = req.getParameter("vnp_ResponseCode");

        HttpSession session = req.getSession(false);
        FilmBooking filmBooking = (FilmBooking) session.getAttribute("filmBooking");

        if (vnPayRespCode.equals("00")) {
            if (filmBookingServices.save(filmBooking)) {
                Showtime bookedShowtime = filmBooking.getShowtime();
                if (bookedShowtime.bookSeats(filmBooking.getSeats())) {
                    showtimeServices.update(bookedShowtime);
                }
            }

            System.out.println("OK");
        } else {
            Showtime bookedShowtime = filmBooking.getShowtime();
            if (bookedShowtime.releaseSeats(filmBooking.getSeats()))
                showtimeServices.update(bookedShowtime);

            System.out.println("Not OK");
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        hibernateSessionProvider = null;
        filmBookingServices = null;
        showtimeServices = null;
    }
}
