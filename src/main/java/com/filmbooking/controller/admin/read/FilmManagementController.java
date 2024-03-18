package com.filmbooking.controller.admin.read;


import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.PaginationUtils;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "filmManagement", value = "/admin/management/film")
public class FilmManagementController extends HttpServlet {
    private IFilmServices filmServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) filmServices.getTotalRecords() / LIMIT);
        int offset = PaginationUtils.handlesPagination(LIMIT, currentPage, totalPages, req, resp);

        // if page valid (offset != -2)
        if (offset != -2) {
            // if page has data (offset != -1)
            if (offset != -1) {
                List<Film> films = filmServices.getByOffset(offset, LIMIT);

                req.setAttribute("filmsData", films);
                // set page url for pagination
                req.setAttribute("pageUrl", "admin/management/film");

            }

            req.setAttribute("pageTitle", "filmManagementTitle");
            RenderViewUtils.renderViewToLayout(req, resp, WebAppPathUtils.getAdminPagesPath("film-management.jsp"), WebAppPathUtils.getLayoutPath("master.jsp"));
        }

        hibernateSessionProvider.closeSession();

    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }
}
