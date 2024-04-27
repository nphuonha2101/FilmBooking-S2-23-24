package com.filmbooking.controller.admin.read;

import com.filmbooking.controller.apis.LogAPI;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.LogModel;
import com.filmbooking.services.impls.LogModelServicesImpl;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "logManagement", value = "/admin/management/log")
public class LogManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Quản lý Log");
        RenderViewUtils.renderViewToLayout(req, resp, WebAppPathUtils.getAdminPagesPath("log-management.jsp"),WebAppPathUtils.getLayoutPath("master.jsp"));

    }
}
