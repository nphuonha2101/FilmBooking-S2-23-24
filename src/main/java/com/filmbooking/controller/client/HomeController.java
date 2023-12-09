package com.filmbooking.controller.client;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "home", value = "/home")
public class HomeController extends HttpServlet {
    private IFilmServices filmServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 8;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = 0;
        int currentPage = 1;
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
            offset = (currentPage - 1) * LIMIT;
        }

        int totalPages = (int) Math.ceil((double) filmServices.getTotalRecords() / LIMIT);

        if (currentPage < 0 || currentPage > totalPages)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        else {
            List<Film> films = filmServices.getByOffset(offset, LIMIT);

            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("filmsData", films);

            req.setAttribute("pageTitle", "homeTitle");
            RenderViewUtils.renderViewToLayout(req, resp,
                    ContextPathUtils.getClientPagesPath("home.jsp"),
                    ContextPathUtils.getLayoutPath("master.jsp"));
        }
        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }

}
