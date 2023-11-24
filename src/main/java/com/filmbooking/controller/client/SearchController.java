package com.filmbooking.controller.client;

import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.ContextPathUtils;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();

        String searchQuery = req.getParameter("search");

        List<Film> searchFilmList = filmServices.getByFilmName(searchQuery);

        if (searchFilmList.isEmpty()) {
            req.setAttribute("statusCodeErr", StatusCodeEnum.FILM_NOT_FOUND.getStatusCode());
            req.setAttribute("searchQuery", searchQuery);
        }
        else
            req.setAttribute("filmsData", searchFilmList);


        req.setAttribute("pageTitle", "searchResultsTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("search-results.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    public void destroy() {
        filmServices = null;
    }
}
