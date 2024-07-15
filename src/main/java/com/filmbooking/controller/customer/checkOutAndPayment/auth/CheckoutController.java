package com.filmbooking.controller.customer.checkOutAndPayment.auth;

/*
 *  @created 13/01/2024 - 9:00 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.enumsAndConstants.enums.PaymentStatus;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.payment.VNPay;
import com.filmbooking.services.impls.FilmBookingServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth/checkout")
public class CheckoutController extends HttpServlet {
    private FilmBookingServicesImpl filmBookingServices;
    private ShowtimeServicesImpl showtimeServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Page checkoutPage = new ClientPage(
                "checkoutTitle",
                "checkout",
                "master"
        );
        checkoutPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmBookingServices = new FilmBookingServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();

        String paymentMethod = req.getParameter("payment-method");

        HttpSession session = req.getSession(false);
        FilmBooking filmBooking = (FilmBooking) session.getAttribute("filmBooking");

        if (!filmBooking.isExpired()) {
            if (paymentMethod.equalsIgnoreCase("cash")) {
                filmBooking.setPaymentStatus("pending");
                PaymentController.handlePayment(req, resp, filmBooking, showtimeServices, filmBookingServices, PaymentStatus.PENDING);
            }
            // get vnpay payment url
            else {
                double amount = filmBooking.getTotalFee();
                String locate = "";
                String language = (String) session.getAttribute("lang");

                if (language == null || language.equals("default"))
                    locate = "vn";
                else
                    locate = "us";

                String orderInfo = "";
                if (locate.equals("vn"))
                    orderInfo = "THANH TOAN FILMBOOKING ";
                else
                    orderInfo = "FILM BOOKING PAYMENT ";

                orderInfo += filmBooking.getFilmBookingID() + " - " + filmBooking.getShowtime().getFilm().getFilmName() + " - " + filmBooking.getUser().getUsername() + " - " + filmBooking.getBookingDate();

                VNPay vnPay = new VNPay();
                String paymentUrl = vnPay.addAmount(amount)
                        .addTxnRef(filmBooking.getVnpayTxnRef())
                        .addOrderInfo(orderInfo)
                        .addLocale(req)
                        .addCustomerIP(req)
                        .getPaymentURL();

                resp.sendRedirect(paymentUrl);
            }
        } else
            PaymentController.handlePayment(req, resp, filmBooking, showtimeServices, filmBookingServices, PaymentStatus.SUCCESS);
    }

    @Override
    public void destroy() {
        filmBookingServices = null;
        showtimeServices = null;
    }
}

