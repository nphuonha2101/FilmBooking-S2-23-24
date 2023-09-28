package com.filmbooking.controller.admin;

import com.filmbooking.DAOservices.FilmDAOServicesImpl;
import com.filmbooking.DAOservices.FilmGenreDAOServicesImpl;
import com.filmbooking.DAOservices.IFilmDAOServices;
import com.filmbooking.DAOservices.IFilmGenreDAOServices;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteFilm", value = "/delete-film")
public class DeleteFilmController extends HttpServlet {
    private IFilmDAOServices filmDAOServices;
    private IFilmGenreDAOServices filmGenreDAOServices;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmDAOServices = new FilmDAOServicesImpl();
        filmGenreDAOServices = new FilmGenreDAOServicesImpl();

        String filmID = req.getParameter("film-id_hidden");
        System.out.println(filmID);

        filmGenreDAOServices.removeFilmGenresWithFilmID(filmID);
        filmDAOServices.deleteFilmByFilmID(filmID);


        resp.sendRedirect("admin");


    }

    @Override
    public void destroy() {
        filmDAOServices = null;
    }
}
