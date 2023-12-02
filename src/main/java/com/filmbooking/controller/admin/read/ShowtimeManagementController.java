package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
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
    private HibernateSessionProvider hibernateSessionProvider;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        System.out.println(showtimeServices.countAvailableSeats());

        req.setAttribute("showtimeList", showtimeServices.getAll());
        req.setAttribute("availableSeats", showtimeServices.countAvailableSeats());
        req.setAttribute("pageTitle", "showtimeManagementTitle");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("showtime-management.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

//        RenderViewUtils.updateView(req, resp,
//                ContextPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
