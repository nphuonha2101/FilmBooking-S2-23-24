package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.PaginationUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="showtimeManagement", value="/admin/management/showtime")
public class ShowtimeManagementController extends HttpServlet {
    private ShowtimeServicesImpl showtimeServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);


        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) showtimeServices.getTotalRecordRows() / LIMIT);
        int offset = PaginationUtils.handlesPagination(LIMIT, currentPage, totalPages, req, resp);

        // if page valid (offset != -2)
        if (offset != -2) {
            // if page has data (offset != -1)
            if (offset != -1) {
                List<Showtime> showtimeList = showtimeServices.getByOffset(offset, LIMIT).getMultipleResults();

                req.setAttribute("showtimeList", showtimeList);
                // set page url for pagination
                req.setAttribute("pageUrl", "admin/management/showtime");

                req.setAttribute("availableSeats", showtimeServices.countAvailableSeats());
            }

            req.setAttribute("pageTitle", "showtimeManagementTitle");
            RenderViewUtils.renderViewToLayout(req, resp,
                    WebAppPathUtils.getAdminPagesPath("showtime-management.jsp"),
                    WebAppPathUtils.getLayoutPath("master.jsp"));
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
