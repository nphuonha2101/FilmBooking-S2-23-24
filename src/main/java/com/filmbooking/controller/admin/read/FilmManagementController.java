package com.filmbooking.controller.admin.read;

import com.filmbooking.model.Film;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.Pagination;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "filmManagement", value = "/admin/management/film")
public class FilmManagementController extends HttpServlet {

    private static final int LIMIT = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilmServicesImpl filmServices = new FilmServicesImpl();


        Page filmManagementPage = new AdminPage(
                "filmManagementTitle",
                "film-management",
                "master"
        );

        Pagination<Film> pagination = new Pagination<>(filmServices, req, resp, LIMIT, "/admin/management/film");
        filmManagementPage.putAttribute("filmsData", pagination.getPaginatedRecords());

        filmManagementPage.render(req, resp);
    }

}
