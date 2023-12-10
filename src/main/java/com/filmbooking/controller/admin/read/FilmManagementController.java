package com.filmbooking.controller.admin.read;


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

@WebServlet(name = "filmManagement", value = "/film-management")
public class FilmManagementController extends HttpServlet {
    private IFilmServices filmServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        int offset = 0;
        int currentPage = 1;

        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
            offset = (currentPage - 1) * LIMIT;
        }

        int totalPages = (int) Math.ceil((double) filmServices.getTotalRecords() / LIMIT);

        if (currentPage < 0 || currentPage > totalPages) resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        else {
            List<Film> films = filmServices.getByOffset(offset, LIMIT);

            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("filmsData", films);
            req.setAttribute("pageUrl", "film-management");

            req.setAttribute("pageTitle", "filmManagementTitle");

            RenderViewUtils.renderViewToLayout(req, resp, ContextPathUtils.getAdminPagesPath("film-management.jsp"), ContextPathUtils.getLayoutPath("master.jsp"));
        }

        hibernateSessionProvider.closeSession();

    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }
}
