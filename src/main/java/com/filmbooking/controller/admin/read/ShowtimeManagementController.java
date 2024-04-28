package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.services.logProxy.ShowtimeServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.PaginationUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="showtimeManagement", value="/admin/management/showtime")
public class ShowtimeManagementController extends HttpServlet {
    private ShowtimeServicesLogProxy<Showtime> showtimeServices;
    private CRUDServicesLogProxy<Showtime> showtimeServicesCRUD;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new ShowtimeServicesLogProxy<>(new ShowtimeServicesImpl(), req, hibernateSessionProvider);
        showtimeServicesCRUD = new CRUDServicesLogProxy<>(new ShowtimeServicesImpl(), req, hibernateSessionProvider);

        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) showtimeServicesCRUD.getTotalRecordRows() / LIMIT);
        int offset = PaginationUtils.handlesPagination(LIMIT, currentPage, totalPages, req, resp);

        Page showtimeManagementPage = new AdminPage();
        showtimeManagementPage.setPageTitle("showtimeManagementTitle");
        showtimeManagementPage.setPage("showtime-management");
        showtimeManagementPage.setLayout("master");

        // if page valid (offset != -2)
        if (offset != -2) {
            // if page has data (offset != -1)
            if (offset != -1) {
                List<Showtime> showtimeList = showtimeServicesCRUD.getByOffset(offset, LIMIT).getMultipleResults();

                showtimeManagementPage.putAttribute("showtimeList", showtimeList);
                showtimeManagementPage.putAttribute("availableSeats", showtimeServices.getAvailableSeatsByShowtimeId());
                showtimeManagementPage.putAttribute("pageUrl", "admin/management/showtime");
            }

            showtimeManagementPage.render(req, resp);
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
