package com.filmbooking.controller;

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
        filmDAOServices = new FilmDAOServicesImpl();

        req.setAttribute("pageTitle", "Film Booking - Trang Admin");

        System.out.println(filmDAOServices.getAll().size());
        req.setAttribute("filmsData", filmDAOServices.getAll());
//        req.setAttribute("formContentPage",
//                ContextPathUtils.getAdminPagesPath("add-film.jsp"));
//

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getAdminPagesPath("admin.jsp"));

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("admin.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));
    }
}
