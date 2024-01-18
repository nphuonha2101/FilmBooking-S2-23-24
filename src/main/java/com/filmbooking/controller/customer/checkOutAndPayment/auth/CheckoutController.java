package com.filmbooking.controller.customer.checkOutAndPayment.auth;

/*
 *  @created 13/01/2024 - 9:00 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.payment.VNPay;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth/checkout")
public class CheckoutController extends HttpServlet {
    private HibernateSessionProvider hibernateSessionProvider;
    private IFilmBookingServices filmBookingServices;
    private IShowtimeServices showtimeServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "checkoutTitle");
        RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getClientPagesPath("checkout.jsp"),
                PathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmBookingServices = new FilmBookingServicesImpl(hibernateSessionProvider);
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        String paymentMethod = req.getParameter("payment-method");

        HttpSession session = req.getSession(false);
        FilmBooking filmBooking = (FilmBooking) session.getAttribute("filmBooking");

        if (paymentMethod.equalsIgnoreCase("cash")) {
            if (filmBookingServices.save(filmBooking)) {
                Showtime bookedShowtime = filmBooking.getShowtime();
                if (bookedShowtime.bookSeats(filmBooking.getSeats())) {
                    showtimeServices.update(bookedShowtime);
                }
            }
        }
        // get vnpay payment url
        else {
            int amount = (int) Math.round(filmBooking.getTotalFee());
            String locate = "";
            String language = (String) req.getAttribute("lang");

            if (language == null || language.equals("default"))
                locate = "vn";
            else
                locate = "us";

            String orderInfo = "";
            if (locate.equals("vn"))
                orderInfo = "THANH TOAN FILM BOOKING ";
            else
                orderInfo = "FILM BOOKING PAYMENT ";

            orderInfo += +filmBooking.getFilmBookingID() + " - " + filmBooking.getShowtime().getFilm().getFilmName() + " - " + filmBooking.getUser().getUsername() + " - " + filmBooking.getBookingDate();

            String paymentUrl = new VNPay().getPaymentURL(amount, orderInfo, req.getRemoteAddr(), locate);

            resp.sendRedirect(paymentUrl);
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

