package com.filmbooking.controller.admin.update;

import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.GenreServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.FilmServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.UUIDUtils;
import com.filmbooking.utils.fileUtils.FileUploadUtils;
import com.filmbooking.utils.fileUtils.FileUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "editFilm", value = "/admin/edit/film")
@MultipartConfig
public class EditFilmController extends HttpServlet {
    private FilmServicesImpl filmServices;
    private Film editFilm;
    private GenreServicesImpl genreServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();
        genreServices = new GenreServicesImpl();

        String filmSlug = req.getParameter("film");
        editFilm = filmServices.getBySlug(filmSlug);
        if (editFilm == null) {
            editFilm = (Film) req.getSession().getAttribute("editFilm");
        }

        // retrieve film genres of film
        StringBuilder filmGenreIDs = new StringBuilder();
        List<Genre> filmGenreList = editFilm.getGenreList();

        filmGenreList.stream().forEach(genre -> {
            filmGenreIDs.append(genre.getGenreID());
            filmGenreIDs.append(" ");
        });

        Page editFilmPage = new AdminPage(
                "editFilmTitle",
                "edit-film",
                "master");

        req.getSession().setAttribute("editFilm", editFilm);
        editFilmPage.putAttribute("editFilm", editFilm);
        editFilmPage.putAttribute("genres", genreServices.selectAll());
        editFilmPage.putAttribute("filmGenresStr", editFilm.getFilmGenresStr());
        editFilmPage.putAttribute("filmGenreIDs", filmGenreIDs.toString().trim());

        editFilmPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilmServicesLogProxy filmServicesLog = new FilmServicesLogProxy(filmServices, req);

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
            req.setAttribute("statusCodeErr", StatusCodeEnum.PLS_FILL_ALL_REQUIRED_FIELDS.getStatusCode());
            doGet(req, resp);
            return;
        }

        editFilm = (Film) req.getSession().getAttribute("editFilm");

        editFilm.setFilmName(filmName);
        editFilm.setFilmPrice(filmPrice);
        editFilm.setDirector(filmDirector);
        editFilm.setCast(filmActors);
        editFilm.setFilmLength(filmLength);
        editFilm.setFilmDescription(filmDescription);
        editFilm.setFilmTrailerLink(filmTrailerLink);

        // if not change image
        if (filmImgName.isEmpty()) {
            if (filmServicesLog.update(editFilm, filmGenreIDs))
                req.setAttribute("statusCodeSuccess", StatusCodeEnum.UPDATE_FILM_SUCCESSFUL.getStatusCode());
            else
                req.setAttribute("statusCodeErr", StatusCodeEnum.UPDATE_FILM_FAILED.getStatusCode());

            doGet(req, resp);
        } else {
            String uuidFileName = UUIDUtils.generateRandomUUID(filmImgName);
            String filmImgPath = WebAppPathUtils.getUploadFileRelativePath(uuidFileName);

            // set new img file and upload to server
            if (FileUploadUtils.uploadFile(req, uuidFileName, "upload-img")) {

                // delete old img file
                File oldFile = new File(FileUtils.getRealWebappPath(req) + editFilm.getImgPath());
                oldFile.delete();

                System.out.println(oldFile.getAbsolutePath());

                editFilm.setImgPath(filmImgPath);

                if (!filmServicesLog.update(editFilm, filmGenreIDs))
                    req.setAttribute("statusCodeErr", StatusCodeEnum.UPDATE_FILM_FAILED.getStatusCode());
                else
                    req.setAttribute("statusCodeSuccess", StatusCodeEnum.UPDATE_FILM_SUCCESSFUL.getStatusCode());

                doGet(req, resp);
            } else {
                req.setAttribute("statusCodeErr", StatusCodeEnum.UPDATE_FILM_FAILED.getStatusCode());
                doGet(req, resp);
            }
        }

//        resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/admin/management/film"));
    }

    @Override
    public void destroy() {
        filmServices = null;
        editFilm = null;
        genreServices = null;
    }
}

