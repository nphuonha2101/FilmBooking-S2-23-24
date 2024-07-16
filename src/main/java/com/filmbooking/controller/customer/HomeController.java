package com.filmbooking.controller.customer;

import com.filmbooking.model.Film;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.Pagination;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "home", value = "/home")
public class HomeController extends HttpServlet {
    private FilmServicesImpl filmServices;
    private static final int LIMIT = 8;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();

        Page homePage = new ClientPage(
                "homeTitle",
                "home",
                "master"
        );

        Pagination<Film> pagination = new Pagination<>(filmServices, req, resp, LIMIT, "home");

        homePage.putAttribute("filmsData", pagination.getPaginatedRecords());
        homePage.putAttribute("sectionTitle", "newFilmArriveSectionTitle");
        homePage.render(req, resp);

    }

    @Override
    public void destroy() {
        filmServices = null;
    }

}
