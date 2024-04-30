package com.filmbooking.controller.admin.read;

import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "logManagement", value = "/admin/management/log")
public class LogManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Page logManagementPage = new AdminPage(
                "logManagementTitle",
                "log-management",
                "master"
        );

        logManagementPage.render(req, resp);
    }
}
