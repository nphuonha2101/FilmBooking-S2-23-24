package com.filmbooking.controller.customer.checkOutAndPayment.auth;

/*
 *  @created 20/01/2024 - 10:03 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/payment-status")
public class PaymentStatusController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paymentStatus = req.getParameter("status");

        Page paymentStatusPage = new ClientPage(
                "paymentStatusTitle",
                "payment-status",
                "master"
        );

        switch (paymentStatus) {
            case "success" -> {
                paymentStatusPage.putAttribute("statusCode", StatusCodeEnum.PAYMENT_SUCCESSFUL.getStatusCode());
                paymentStatusPage.putAttribute("paymentStatusImg", "success.png");
                paymentStatusPage.putAttribute("paymentMessage", "paymentSuccessMessage");
            }
            case "pending" -> {
                paymentStatusPage.putAttribute("statusCode", StatusCodeEnum.PAYMENT_PENDING.getStatusCode());
                paymentStatusPage.putAttribute("paymentStatusImg", "process.png");
                paymentStatusPage.putAttribute("paymentMessage", "paymentPendingMessage");
            }
            case "failed" -> {
                paymentStatusPage.putAttribute("statusCode", StatusCodeEnum.PAYMENT_FAILED.getStatusCode());
                paymentStatusPage.putAttribute("paymentStatusImg", "failed.png");
                paymentStatusPage.putAttribute("paymentMessage", "paymentFailedMessage");
            }
        }

        paymentStatusPage.render(req, resp);
    }
}
