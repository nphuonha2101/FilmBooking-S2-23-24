package com.filmbooking.controller.client.account.auth;

import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/account-info")

public class AccountInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("pageTitle", "accountInfoTitle");
        System.out.println(req.getSession().getAttribute("loginUser"));
        RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getClientPagesPath("account-info.jsp"),
                PathUtils.getLayoutPath("master.jsp"));
    }

}
