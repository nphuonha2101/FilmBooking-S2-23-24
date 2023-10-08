package com.filmbooking.controller.admin;

import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="BookingManagementController", value="/booking-management")
public class BookingManagementController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Film Booking - Quản lý đặt vé");
        req.setAttribute("sectionTitle", "Quản lý đặt vé");
        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getAdminPagesPath("booking-management.jsp"));
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("booking-management.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));
    }
}
