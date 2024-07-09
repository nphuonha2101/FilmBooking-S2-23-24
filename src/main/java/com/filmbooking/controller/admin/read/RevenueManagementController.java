package com.filmbooking.controller.admin.read;

import com.filmbooking.model.Film;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.Pagination;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "revenueManagement", value = "/admin/management/revenue")
public class RevenueManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Page revenueManagementPage = new AdminPage(
                "revenueManagementTitle",
                "revenue-management",
                "master"
        );

        revenueManagementPage.render(req, resp);
    }
}
