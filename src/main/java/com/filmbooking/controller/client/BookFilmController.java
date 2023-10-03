package com.filmbooking.controller.client;

import com.filmbooking.DAOservices.FilmDAOServicesImpl;
import com.filmbooking.DAOservices.IFilmDAOServices;
import com.filmbooking.model.Film;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "bookFilm", value="/book-film")
public class BookFilmController extends HttpServlet {
    private IFilmDAOServices filmDAOServices;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmDAOServices = new FilmDAOServicesImpl();
        String filmID = req.getParameter("film-id");
        Film bookedFilm = filmDAOServices.getFilmByID(filmID);

        req.setAttribute("filmData", bookedFilm);

        req.setAttribute("sectionTitle", "Thông tin đặt phim");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("book-film.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));
//        RenderViewUtils.updateView(req, resp, ContextPathUtils.getLayoutPath("master.jsp"));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
