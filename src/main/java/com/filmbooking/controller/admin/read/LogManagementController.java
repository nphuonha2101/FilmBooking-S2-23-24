package com.filmbooking.controller.admin.read;

import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "logManagement", value = "/admin/management/log")
public class LogManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Page logManagementPage = new AdminPage(
                "logManagementTitle",
                "log-management",
                "master"
        );

        ArrayList<String> customStyleSheets = new ArrayList<>();
        customStyleSheets.add("datatable_tailwind.css");
        logManagementPage.setCustomStyleSheets(customStyleSheets);

        logManagementPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String logID = req.getParameter("logID");

        if (message != null && !message.isEmpty() && logID != null && !logID.isEmpty()) {
            System.out.println("Received message: " + message);
            System.out.println("Received logID: " + logID);
        }else {
            System.out.println("Don't received message");
        }
    }
}
