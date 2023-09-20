package com.filmbooking.controller;

import com.filmbooking.DAOservices.FilmDAOServicesImpl;
import com.filmbooking.DAOservices.IFilmDAOServices;
import com.filmbooking.model.Film;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/filmsData")
public class RenderFilmsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IFilmDAOServices filmDAOServices = new FilmDAOServicesImpl();

        List<Film> filmList = filmDAOServices.getAll();

        request.setAttribute("filmsData", filmList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
