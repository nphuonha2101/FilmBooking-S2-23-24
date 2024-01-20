package com.filmbooking.controller.customer;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PaginationUtils;
import com.filmbooking.utils.PathUtils;
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
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) filmServices.getTotalRecords() / LIMIT);
        int offset = PaginationUtils.handlesPagination(LIMIT, currentPage, totalPages, req, resp);

        // if offset == -2, it means that the current page is not valid
        if (offset != -2) {
            // if offset == -1, it means that no data is found
            if (offset != -1) {
                List<Film> films = filmServices.getByOffset(offset, LIMIT);

                req.setAttribute("filmsData", films);
                req.setAttribute("pageUrl", "home");
            } else {
                req.setAttribute("statusCodeErr", StatusCodeEnum.NO_DATA.getStatusCode());
                req.setAttribute("messageDescription", "noData");
            }

            req.setAttribute("sectionTitle", "newFilmArriveSectionTitle");
            req.setAttribute("pageTitle", "homeTitle");
            RenderViewUtils.renderViewToLayout(req, resp,
                    PathUtils.getClientPagesPath("home.jsp"),
                    PathUtils.getLayoutPath("master.jsp"));
        }
        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }

}
