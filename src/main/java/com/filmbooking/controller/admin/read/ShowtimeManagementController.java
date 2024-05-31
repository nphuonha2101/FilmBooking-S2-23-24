package com.filmbooking.controller.admin.read;

import com.filmbooking.model.Showtime;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.Pagination;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="showtimeManagement", value="/admin/management/showtime")
public class ShowtimeManagementController extends HttpServlet {
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShowtimeServicesImpl showtimeServices = new ShowtimeServicesImpl();
        Page showtimeManagementPage = new AdminPage(
                "showtimeManagementTitle",
                "showtime-management",
                "master"
        );

        Pagination<Showtime> pagination = new Pagination<>(showtimeServices, req, resp, LIMIT, "admin/management/showtime");

        showtimeManagementPage.putAttribute("showtimeList", pagination.getPaginatedRecords());
        showtimeManagementPage.putAttribute("availableSeats", showtimeServices.getAvailableSeatsByShowtimeId());
        showtimeManagementPage.render(req, resp);
    }

}
