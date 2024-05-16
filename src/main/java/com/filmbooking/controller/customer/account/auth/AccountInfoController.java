package com.filmbooking.controller.customer.account.auth;

import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.utils.WebAppPathUtils;
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
        System.out.println(req.getSession().getAttribute("loginUser"));

        Page accountInfoPage = new ClientPage(
                "accountInfoTitle",
                "account-info",
                "master"
        );

        accountInfoPage.render(req, resp);
    }

}
