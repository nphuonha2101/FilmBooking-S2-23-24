package com.filmbooking.controller.admin.update;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmGenre;
import com.filmbooking.services.impls.FilmGenreServicesImpl;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.fileUtils.FileUploadUtils;
import com.filmbooking.utils.uuidUtils.UUIDUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "editFilm", value = "/edit-film")
@MultipartConfig
public class EditFilmController extends HttpServlet {
    private FilmServicesImpl filmServices;
    private FilmGenreServicesImpl filmGenreServices;
    private Film editFilm;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();
        filmGenreServices = new FilmGenreServicesImpl();

        String filmId = req.getParameter("film-id_hidden");

        editFilm = filmServices.getByFilmID(filmId);

        req.setAttribute("editFilm", editFilm);

        // retrieve film genres of film
        StringBuilder filmGenreIDs = new StringBuilder();
        List<FilmGenre> filmGenreList = filmGenreServices.getAll();
        int countGenre = 0;
        for (FilmGenre filmGenre : filmGenreList) {
            if (filmGenre.getFilmID().equalsIgnoreCase(filmId)) {
                countGenre++;
                if (countGenre > 1)
                    filmGenreIDs.append(" ").append(filmGenre.getGenreID());
                else filmGenreIDs.append(filmGenre.getGenreID());
            }
        }
        req.setAttribute("filmGenreIDs", filmGenreIDs.toString());

        req.setAttribute("pageTitle", "Film Booking - Chỉnh sửa phim");
        req.setAttribute("sectionTitle", "Chỉnh sửa phim");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("edit-film.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmName = req.getParameter("film-name");
        double filmPrice = Double.parseDouble(req.getParameter("film-price"));
        String filmDirector = req.getParameter("director");
        String filmActors = req.getParameter("actors");
        int filmLength = Integer.parseInt(req.getParameter("film-length"));
        String filmDescription = req.getParameter("film-description");
        String filmTrailerLink = req.getParameter("film-trailer-link");
        String filmImgName = req.getParameter("film-img-name");
        String filmGenres = req.getParameter("genre-ids");

        String[] filmGenreIDArr = filmGenres.split(" ");

        editFilm.setFilmName(filmName);
        editFilm.setFilmPrice(filmPrice);
        editFilm.setDirector(filmDirector);
        editFilm.setCast(filmActors);
        editFilm.setFilmLength(filmLength);
        editFilm.setFilmDescription(filmDescription);
        editFilm.setFilmTrailerLink(filmTrailerLink);

        // if not change image
        if (filmImgName == null || filmImgName.isEmpty())
            filmServices.update(editFilm, filmGenreIDArr);
        else {
            String uuidFileName = UUIDUtils.generateRandomUUID(filmImgName);
            String filmImgPath = ContextPathUtils.getUploadFileRelativePath(uuidFileName);

            editFilm.setImgPath(filmImgPath);
            filmServices.update(editFilm, filmGenreIDArr);
            FileUploadUtils.uploadFile(req, uuidFileName, "upload-img");
        }

        resp.sendRedirect("film-management");

    }

    @Override
    public void destroy() {
        filmServices = null;
        filmGenreServices = null;
    }
}

