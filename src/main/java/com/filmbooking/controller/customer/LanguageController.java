package com.filmbooking.controller.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/lang")
public class LanguageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String languageName = req.getParameter("name");

        HttpSession session = req.getSession();
        session.setAttribute("lang", languageName);

        resp.sendRedirect(req.getHeader("Referer"));
    }
}
