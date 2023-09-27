package com.filmbooking.controller.utils;

import com.filmbooking.DAOservices.FilmDAOServicesImpl;
import com.filmbooking.DAOservices.IFilmDAOServices;
import com.filmbooking.model.Film;
import com.filmbooking.ultils.ImageUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "renderImages", value = "/render-images")
public class RenderImages extends HttpServlet {
    private IFilmDAOServices filmDAOServices;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmDAOServices = new FilmDAOServicesImpl();
        String filmID = req.getParameter("filmID");
        Film foundFilm = filmDAOServices.getFilmByID(filmID);

        String imgPath = foundFilm.getImgPath();
        System.out.println(imgPath);

        byte[] imgData = ImageUtils.convertImageToBytes(imgPath);

        // reference from https://stackoverflow.com/questions/1154254/help-getting-image-from-servlet-to-jsp-page
        resp.setContentType("image/png");
        resp.setContentLength(imgData.length);
        resp.getOutputStream().write(imgData);
    }
}
