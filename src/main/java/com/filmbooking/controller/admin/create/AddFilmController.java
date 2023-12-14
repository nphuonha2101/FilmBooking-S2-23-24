package com.filmbooking.controller.admin.create;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IGenreServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.GenreServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.fileUtils.FileUploadUtils;
import com.filmbooking.utils.uuidUtils.UUIDUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addFilm", value = "/admin/add/film")
@MultipartConfig
public class AddFilmController extends HttpServlet {
    private IFilmServices filmServices;
    private IGenreServices genreServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        genreServices = new GenreServicesImpl(hibernateSessionProvider);

        req.setAttribute("pageTitle", "addFilmTitle");
        req.setAttribute("genres", genreServices.getAll());

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("add-film.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();

        filmServices = new FilmServicesImpl(hibernateSessionProvider);
        genreServices = new GenreServicesImpl(hibernateSessionProvider);

        String fileName = req.getParameter("film-img-name");

        // generate uuid from filename
        fileName = UUIDUtils.generateRandomUUID(fileName);

        String relativeFilePath = ContextPathUtils.getUploadFileRelativePath(fileName);

        String filmName = StringUtils.handlesInputString(req.getParameter("film-name"));
        double filmPrice = Double.parseDouble(req.getParameter("film-price"));
        String filmDirector = StringUtils.handlesInputString(req.getParameter("director"));
        String filmActors = StringUtils.handlesInputString(req.getParameter("actors"));
        int filmLength = Integer.parseInt(req.getParameter("film-length"));
        String filmDescription = StringUtils.handlesInputString(req.getParameter("film-description"));
        String filmTrailerLink = StringUtils.handlesInputString(req.getParameter("film-trailer-link"));
        String[] filmGenreIDs = req.getParameterValues("genre-ids");

        if (filmName.isBlank() || filmDirector.isBlank() || filmActors.isBlank() || filmDescription.isBlank()
                || filmGenreIDs == null || filmGenreIDs.length == 0) {
            return;
        }

        Film newFilm = new Film(filmName, filmPrice, filmDirector, filmActors, filmLength, filmDescription, filmTrailerLink,
                relativeFilePath);

        if (FileUploadUtils.uploadFile(req, fileName, "upload-img")) {
            filmServices.save(newFilm, filmGenreIDs);
            resp.sendRedirect("/admin/management/film");
        } else {
            RenderViewUtils.renderViewToLayout(req, resp,
                    ContextPathUtils.getAdminPagesPath("add-film.jsp"),
                    ContextPathUtils.getLayoutPath("master.jsp"));
        }


        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }
}
