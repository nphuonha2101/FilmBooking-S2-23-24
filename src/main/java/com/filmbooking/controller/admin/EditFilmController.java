package com.filmbooking.controller.admin;

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

@WebServlet(name = "edit-film", value = "/edit-film")
public class EditFilmController extends HttpServlet {
    private IFilmDAOServices filmDAOServices;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmDAOServices = new FilmDAOServicesImpl();
        String filmId = req.getParameter("film-id_hidden");

        Film film = filmDAOServices.getFilmByID(filmId);
        req.setAttribute("filmData", film);

        req.setAttribute("pageTitle", "Film Booking - Chỉnh sửa phim");
        req.setAttribute("sectionTitle", "Chỉnh sửa phim");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("edit-film.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    public void destroy() {
        filmDAOServices = null;
    }
}

