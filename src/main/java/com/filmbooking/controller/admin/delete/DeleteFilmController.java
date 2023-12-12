package com.filmbooking.controller.admin.delete;


import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.fileUtils.FileUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "deleteFilm", value = "/delete-film")
public class DeleteFilmController extends HttpServlet {
    private IFilmServices filmServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        String filmID = req.getParameter("film-id_hidden");

//        System.out.println(filmServices.getByFilmID(filmID));

        Film deletedFilm = filmServices.getByFilmID(filmID);
        if (filmServices.delete(deletedFilm)) {

            // delete film image
            String filmImgFilePath = FileUtils.getRealWebappPath(req) + deletedFilm.getImgPath();
            System.out.println("DeleteFilmController Test: " + filmImgFilePath);
            File file = new File(filmImgFilePath);
            file.delete();

            resp.sendRedirect("admin");
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
