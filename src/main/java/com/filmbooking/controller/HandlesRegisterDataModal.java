package com.filmbooking.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpClient;

@WebServlet(name="handlesRegisterDataModal", value = "/handles-data-modal")
public class HandlesRegisterDataModal extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmId= req.getParameter("filmID");
        HttpSession session = req.getSession();
        session.setAttribute("filmID", filmId);

        req.getRequestDispatcher("index.jsp").include(req, resp);
    }
}
