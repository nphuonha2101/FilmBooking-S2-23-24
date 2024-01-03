package com.filmbooking.controller.admin.update;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IGenreServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.GenreServicesImpl;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.fileUtils.FileUploadUtils;
import com.filmbooking.utils.fileUtils.FileUtils;
import com.filmbooking.utils.UUIDUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "editFilm", value = "/admin/edit/film")
@MultipartConfig
public class EditFilmController extends HttpServlet {
    private IFilmServices filmServices;
    private Film editFilm;
    private IGenreServices genreServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);
        genreServices = new GenreServicesImpl(hibernateSessionProvider);


        String filmSlug = req.getParameter("film");
        editFilm = filmServices.getBySlug(filmSlug);

        req.setAttribute("editFilm", editFilm);
        req.setAttribute("genres", genreServices.getAll());
        req.setAttribute("filmGenresStr", editFilm.getFilmGenresStr());

        // retrieve film genres of film
        StringBuilder filmGenreIDs = new StringBuilder();
        List<Genre> filmGenreList = editFilm.getGenreList();

        filmGenreList.stream().forEach(genre -> {
            filmGenreIDs.append(genre.getGenreID());
            filmGenreIDs.append(" ");
        });

        req.setAttribute("filmGenreIDs", filmGenreIDs.toString().trim());

        req.setAttribute("pageTitle", "editFilmTitle");

        RenderViewUtils.renderViewToLayout(req, resp,
                PathUtils.getAdminPagesPath("edit-film.jsp"),
                PathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        String filmName = StringUtils.handlesInputString(req.getParameter("film-name"));
        double filmPrice = Double.parseDouble(req.getParameter("film-price"));
        String filmDirector = StringUtils.handlesInputString(req.getParameter("director"));
        String filmActors = StringUtils.handlesInputString(req.getParameter("actors"));
        int filmLength = Integer.parseInt(req.getParameter("film-length"));
        String filmDescription = StringUtils.handlesInputString(req.getParameter("film-description"));
        String filmTrailerLink = StringUtils.handlesInputString(req.getParameter("film-trailer-link"));
        String filmImgName = StringUtils.handlesInputString(req.getParameter("film-img-name"));
        String[] filmGenreIDs = req.getParameterValues("genre-ids");

        if (filmName.isBlank() || filmDirector.isBlank() || filmActors.isBlank() || filmDescription.isBlank()
                || filmGenreIDs == null || filmGenreIDs.length == 0) {
            resp.sendRedirect(req.getHeader("referer"));
            return;
        }

        editFilm.setFilmName(filmName);
        editFilm.setFilmPrice(filmPrice);
        editFilm.setDirector(filmDirector);
        editFilm.setCast(filmActors);
        editFilm.setFilmLength(filmLength);
        editFilm.setFilmDescription(filmDescription);
        editFilm.setFilmTrailerLink(filmTrailerLink);

        // if not change image
        if (filmImgName.isEmpty())
            filmServices.update(editFilm, filmGenreIDs);
        else {
            String uuidFileName = UUIDUtils.generateRandomUUID(filmImgName);
            String filmImgPath = PathUtils.getUploadFileRelativePath(uuidFileName);

            // set new img file and upload to server
            if (FileUploadUtils.uploadFile(req, uuidFileName, "upload-img")) {

                // delete old img file
                File oldFile = new File(FileUtils.getRealWebappPath(req) + editFilm.getImgPath());
                oldFile.delete();

                System.out.println(oldFile.getAbsolutePath());

                editFilm.setImgPath(filmImgPath);
                filmServices.update(editFilm, filmGenreIDs);
            }
        }

        resp.sendRedirect(PathUtils.getURLWithContextPath(req, "/admin/management/film"));

        hibernateSessionProvider.closeSession();

    }

    @Override
    public void destroy() {
        filmServices = null;
        editFilm = null;
        hibernateSessionProvider = null;
        genreServices = null;
    }
}

