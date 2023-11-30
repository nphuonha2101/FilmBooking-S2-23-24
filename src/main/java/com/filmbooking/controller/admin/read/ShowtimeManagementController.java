package com.filmbooking.controller.admin.read;

import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
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
    private IShowtimeServices showtimeServices;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeServices = new ShowtimeServicesImpl();

        req.setAttribute("showtimeViewDetails", showtimeServices.getAll());
        req.setAttribute("availableSeats", showtimeServices.countAvailableSeats());
        req.setAttribute("pageTitle", "showtimeManagementTitle");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("showtime-management.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

//        RenderViewUtils.updateView(req, resp,
//                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    public void destroy() {
        showtimeServices = null;
    }
}
