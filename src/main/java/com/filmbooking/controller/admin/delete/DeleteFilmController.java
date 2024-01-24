package com.filmbooking.controller.admin.delete;


import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.fileUtils.FileUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "deleteFilm", value = "/admin/delete/film")
public class DeleteFilmController extends HttpServlet {
    private IFilmServices filmServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        String filmSlug = req.getParameter("film");

        Film deletedFilm = filmServices.getBySlug(filmSlug);
        if (filmServices.delete(deletedFilm)) {

            // delete film image
            String filmImgFilePath = FileUtils.getRealWebappPath(req) + deletedFilm.getImgPath();
            System.out.println("DeleteFilmController Test: " + filmImgFilePath);
            File file = new File(filmImgFilePath);
            file.delete();

            req.setAttribute("statusCodeSuccess", StatusCodeEnum.DELETE_FILM_SUCCESSFUL.getStatusCode());
            doGet(req, resp);
        }
        else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.DELETE_FILM_FAILED.getStatusCode());
            doGet(req, resp);
        }

        hibernateSessionProvider.closeSession();
        System.out.println(hibernateSessionProvider.getSession());
    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }
}
