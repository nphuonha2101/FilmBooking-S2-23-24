package com.filmbooking.controller;

import com.filmbooking.DAOservices.FilmDAOServicesImpl;
import com.filmbooking.DAOservices.IFilmDAOServices;
import com.filmbooking.model.Film;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet(name = "addFilm", value = "/add-film")
@MultipartConfig
public class AddFilmController extends HttpServlet {
    private IFilmDAOServices filmDAOServices;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String fileName = req.getParameter("film-img-path");
//        System.out.println(fileName);

        boolean saveSuccessful = false;

        String fileName = req.getParameter("film-img-path");
        String filePath = ContextPathUtils.getFileUploadPath(fileName);

        req.setAttribute("imgPath", filePath);


        String filmID = req.getParameter("film-id");
        String filmName = req.getParameter("film-name");
        double filmPrice = Double.parseDouble(req.getParameter("film-price"));
        String filmGenre = req.getParameter("film-genre");

        Film newFilm = new Film(filmID, filmName, filmPrice, "", filmGenre, filePath);
        filmDAOServices = new FilmDAOServicesImpl();

        saveSuccessful = filmDAOServices.saveFilm(newFilm);

        Part fileUploadPart = req.getPart("upload-img");
        fileUploadPart.write(filePath);

//        if (saveSuccessful)
//            req.setAttribute("successfulMessage", "Thêm phim " + "\"" + fileName + "\"" + " thành công!");

        resp.sendRedirect("admin");
    }
}
