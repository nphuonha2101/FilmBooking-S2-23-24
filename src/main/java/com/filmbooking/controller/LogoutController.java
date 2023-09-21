package com.filmbooking.controller;

import com.filmbooking.ultils.ContextPathUltil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="logout", value = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        request.setAttribute("dynamicContents", ContextPathUltil.getClientPagesPath("login.jsp"));


        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ContextPathUltil.getLayoutPath("master.jsp"));
        requestDispatcher.forward(request, response);
    }
}
