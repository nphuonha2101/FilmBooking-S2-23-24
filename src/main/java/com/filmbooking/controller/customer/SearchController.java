package com.filmbooking.controller.customer;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    private IFilmServices filmServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        String searchQuery = req.getParameter("q");
        double beginPriceNumber = Double.parseDouble(req.getParameter("beginPrice"));
        double endPriceNumber = Double.parseDouble(req.getParameter("endPrice"));

        List<Film> searchFilmList = filmServices.searchFilms(searchQuery, beginPriceNumber, endPriceNumber);

        if (searchFilmList.isEmpty()) {
            req.setAttribute("statusCodeErr", StatusCodeEnum.FILM_NOT_FOUND.getStatusCode());
            req.setAttribute("searchQuery", "\"" + searchQuery + "\"");
            req.setAttribute("messageDescription", "filmNotFoundWithKeyword");
        } else
            req.setAttribute("filmsData", searchFilmList);

        req.setAttribute("sectionTitle", "searchResultsSectionTitle");
        req.setAttribute("pageTitle", "searchResultsTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                PathUtils.getClientPagesPath("home.jsp"),
                PathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }
}
