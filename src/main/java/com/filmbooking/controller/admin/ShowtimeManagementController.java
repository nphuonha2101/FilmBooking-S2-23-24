package com.filmbooking.controller.admin;

import com.filmbooking.services.IShowtimeViewServices;
import com.filmbooking.services.impls.ShowtimeViewServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/showtime-management")
public class ShowtimeManagementController extends HttpServlet {
    private IShowtimeViewServices showtimeViewServices;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeViewServices = new ShowtimeViewServicesImpl();

        req.setAttribute("sectionTitle", "Quản lý suất chiếu");
        req.setAttribute("showtimeViewDetails", showtimeViewServices.getAll());
        req.setAttribute("pageTitle", "Trang Admin - Quản lý suất chiếu");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("showtime-management.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    public void destroy() {
        showtimeViewServices = null;
    }
}
