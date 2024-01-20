package com.filmbooking.controller.customer.checkOutAndPayment.auth;

/*
 *  @created 20/01/2024 - 10:03 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
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

        switch (paymentStatus) {
            case "success" -> {
                req.setAttribute("statusCode", StatusCodeEnum.PAYMENT_SUCCESSFUL.getStatusCode());
                req.setAttribute("paymentStatusImg", "success.png");
                req.setAttribute("paymentMessage", "paymentSuccessMessage");
            }
            case "pending" -> {
                req.setAttribute("statusCode", StatusCodeEnum.PAYMENT_PENDING.getStatusCode());
                req.setAttribute("paymentStatusImg", "process.png");
                req.setAttribute("paymentMessage", "paymentPendingMessage");
            }
            case "failed" -> {
                req.setAttribute("statusCode", StatusCodeEnum.PAYMENT_FAILED.getStatusCode());
                req.setAttribute("paymentStatusImg", "failed.png");
                req.setAttribute("paymentMessage", "paymentFailedMessage");
            }
        }

        req.setAttribute("pageTitle", "paymentResultTitle");
        RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getClientPagesPath("payment-status.jsp"),
                PathUtils.getLayoutPath("master.jsp"));
    }
}
