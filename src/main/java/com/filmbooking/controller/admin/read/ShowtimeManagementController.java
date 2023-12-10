package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
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
import java.util.List;

@WebServlet("/showtime-management")
public class ShowtimeManagementController extends HttpServlet {
    private IShowtimeServices showtimeServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        int offset = 0;
        int currentPage = 1;

        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
            offset = (currentPage - 1) * LIMIT;
        }

        int totalPages = (int) Math.ceil((double) showtimeServices.getTotalRecords() / LIMIT);

        if (currentPage < 0 || currentPage > totalPages)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        else {
            List<Showtime> showtimeList = showtimeServices.getByOffset(offset, LIMIT);

            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("showtimeList", showtimeList);
            req.setAttribute("pageUrl", "showtime-management");

            req.setAttribute("availableSeats", showtimeServices.countAvailableSeats());
            req.setAttribute("pageTitle", "showtimeManagementTitle");

            RenderViewUtils.renderViewToLayout(req, resp,
                    ContextPathUtils.getAdminPagesPath("showtime-management.jsp"),
                    ContextPathUtils.getLayoutPath("master.jsp"));

//        RenderViewUtils.updateView(req, resp,
//                ContextPathUtils.getLayoutPath("master.jsp"));
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
