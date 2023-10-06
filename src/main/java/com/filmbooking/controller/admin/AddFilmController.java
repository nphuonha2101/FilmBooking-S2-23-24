package com.filmbooking.controller.admin;

import com.filmbooking.DAOservices.FilmDAOServicesImpl;
import com.filmbooking.DAOservices.FilmGenreDAOServicesImpl;
import com.filmbooking.DAOservices.IFilmDAOServices;
import com.filmbooking.DAOservices.IFilmGenreDAOServices;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmGenre;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.FileUploadUtils;
import com.filmbooking.ultils.FileUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addFilm", value = "/add-film")
@MultipartConfig
public class AddFilmController extends HttpServlet {
    private IFilmDAOServices filmDAOServices;
    private IFilmGenreDAOServices filmGenreDAOServices;
    private FileUtils fileUtils;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmDAOServices = new FilmDAOServicesImpl();
        filmGenreDAOServices = new FilmGenreDAOServicesImpl();

        req.setAttribute("sectionTitle", "Thêm phim");
        req.setAttribute("pageTitle", "Trang Admin - Thêm phim");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("add-film.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmDAOServices = new FilmDAOServicesImpl();
        filmGenreDAOServices = new FilmGenreDAOServicesImpl();

        String fileName = req.getParameter("film-img-name");
        String relativeFilePath = ContextPathUtils.getUploadFileRelativePath(fileName);
        fileUtils = new FileUtils(FileUtils.getRealContextPath(req) + ContextPathUtils.getUploadFolderPath());


        String filmID = req.getParameter("film-id");
        String filmName = req.getParameter("film-name");
        double filmPrice = Double.parseDouble(req.getParameter("film-price"));
        String filmDirector = req.getParameter("director");
        String filmActors = req.getParameter("actors");
        int filmLength = Integer.parseInt(req.getParameter("film-length"));

        String filmGenreIDs = req.getParameter("genre-ids");
        String[] filmGenreIDArr = filmGenreIDs.split(",");

        Film newFilm = new Film(filmID, filmName, filmPrice, filmDirector, filmActors, filmLength, "", relativeFilePath);

        boolean addFilmResult = filmDAOServices.saveFilm(newFilm);

        for (String filmGenreID : filmGenreIDArr
        ) {
            FilmGenre filmGenre = new FilmGenre(filmID, filmGenreID);
            System.out.println("AddFilmController Test: " + filmGenreDAOServices.saveFilmGenre(filmGenre));
        }
        if (fileUtils.countDuplicateFile(fileName) == 0)
            FileUploadUtils.uploadFile(req, fileName, "upload-img");
        else {
            fileUtils.handlesFileName(fileName);
            FileUploadUtils.uploadFile(req, fileName, "upload-img");
        }

        resp.sendRedirect("admin");
}

    @Override
    public void destroy() {
        filmDAOServices = null;
    }
}
