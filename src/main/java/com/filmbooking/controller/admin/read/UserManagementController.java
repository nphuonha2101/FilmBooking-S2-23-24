package com.filmbooking.controller.admin.read;

import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/management/user")
public class UserManagementController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Page userManagementPage = new AdminPage(
                "userManagementTitle",
               "user-management",
                "master"
        );
        userManagementPage.render(req, resp);
    }
}
