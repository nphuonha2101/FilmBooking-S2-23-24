package com.filmbooking.controller.admin;


import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.IFilmServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteFilm", value = "/delete-film")
public class DeleteFilmController extends HttpServlet {
    private IFilmServices filmServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();

        String filmID = req.getParameter("film-id_hidden");
        System.out.println("DeleteFilmController Test: " + filmID);

        System.out.println(filmServices.getByFilmID(filmID));

        filmServices.delete(filmServices.getByFilmID(filmID));
//
        resp.sendRedirect("admin");


    }

    @Override
    public void destroy() {
        filmServices = null;
    }
}
