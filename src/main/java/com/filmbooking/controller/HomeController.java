package com.filmbooking.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="home", value = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userLoginSession = req.getSession();
        String loginUsername = (String) userLoginSession.getAttribute("username");
        if (loginUsername != null) {
            req.setAttribute("navigationComponent", "/views/components/already-login-nav.jsp");
            req.setAttribute("dynamicContents", "/views/pages/client/home.jsp");

        }
        else {
            req.setAttribute("navigationComponent", "/views/components/not-login-nav.jsp");
            req.setAttribute("dynamicContents", "/views/pages/client/login.jsp");
        }

        RequestDispatcher homeDispatcher = req.getRequestDispatcher("/views/layout/master.jsp");
        homeDispatcher.forward(req, resp);
    }
}
