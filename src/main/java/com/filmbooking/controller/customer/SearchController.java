package com.filmbooking.controller.customer;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.utils.WebAppPathUtils;
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
    private FilmServicesImpl filmServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        String searchQuery = req.getParameter("q");
        double beginPriceNumber = Double.parseDouble(req.getParameter("begin-price"));
        double endPriceNumber = Double.parseDouble(req.getParameter("end-price"));

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
                WebAppPathUtils.getClientPagesPath("home.jsp"),
                WebAppPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }
}
