package com.filmbooking.controller.admin.delete;


import com.filmbooking.model.Film;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.utils.ContextPathUtils;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();

        filmServices.openSession();

        String filmID = req.getParameter("film-id_hidden");
        System.out.println("DeleteFilmController Test: " + filmID);

        System.out.println(filmServices.getByFilmID(filmID));

        Film deletedFilm = filmServices.getByFilmID(filmID);
        filmServices.delete(deletedFilm);

        // delete film image
        String filmImgFilePath = FileUtils.getRealWebappPath(req) + deletedFilm.getImgPath();
        System.out.println("DeleteFilmController Test: " + filmImgFilePath);
        File file = new File(filmImgFilePath);
        file.delete();

//
        resp.sendRedirect("admin");

        filmServices.closeSession();


    }

    @Override
    public void destroy() {
        filmServices = null;
    }
}
