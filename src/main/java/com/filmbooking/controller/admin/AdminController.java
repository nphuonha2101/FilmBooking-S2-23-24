package com.filmbooking.controller.admin;

import com.filmbooking.DAOservices.FilmDAOServicesImpl;
import com.filmbooking.DAOservices.IFilmDAOServices;
import com.filmbooking.model.Film;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin", value = "/admin")
public class AdminController extends HttpServlet {
    private IFilmDAOServices filmDAOServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = (String) req.getSession().getAttribute("username");
        String accountRole = (String) req.getSession().getAttribute("accountRole");

        System.out.println(accountRole);
        if (accountRole == null) {
            resp.sendRedirect("login");
            return;
        }
        if (!accountRole.equals("admin")) {
            resp.sendRedirect("home");
            return;
        }
        resp.sendRedirect("film-management");
    }
}
