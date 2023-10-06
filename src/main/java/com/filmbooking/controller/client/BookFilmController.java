package com.filmbooking.controller.client;

import com.filmbooking.model.Film;
import com.filmbooking.services.FilmServicesImpl;
import com.filmbooking.services.IFilmServices;
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
    private IFilmServices filmServices;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();

        String filmID = req.getParameter("film-id");
        Film bookedFilm = filmServices.getByFilmID(filmID);

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
