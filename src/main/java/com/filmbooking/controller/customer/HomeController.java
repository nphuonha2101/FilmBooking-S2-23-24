package com.filmbooking.controller.customer;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.utils.PaginationUtils;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "home", value = "/home")
public class HomeController extends HttpServlet {
    private FilmServicesImpl filmServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 8;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) filmServices.getTotalRecordRows() / LIMIT);
        int offset = PaginationUtils.handlesPagination(LIMIT, currentPage, totalPages, req, resp);

        Page homePage = new ClientPage(
                "homeTitle",
                "home",
                "master"
        );

        // if offset == -2, it means that the current page is not valid
        if (offset != -2) {
            // if offset == -1, it means that no data is found
            if (offset != -1) {
                List<Film> films = filmServices.getByOffset(offset, LIMIT).getMultipleResults();

                homePage.putAttribute("filmsData", films);
                homePage.putAttribute("pageUrl", "home");
            } else {
                homePage.putAttribute("statusCodeErr", StatusCodeEnum.NO_DATA.getStatusCode());
                homePage.putAttribute("messageDescription", "noData");
            }

            homePage.putAttribute("sectionTitle", "newFilmArriveSectionTitle");
            homePage.render(req, resp);
        }
        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }

}
