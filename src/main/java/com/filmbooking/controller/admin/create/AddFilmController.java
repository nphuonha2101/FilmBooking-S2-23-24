package com.filmbooking.controller.admin.create;

import com.filmbooking.model.Film;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.FilmServicesImpl;
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

@WebServlet(name = "addFilm", value = "/add-film")
@MultipartConfig
public class AddFilmController extends HttpServlet {
    private IFilmServices filmServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();

        req.setAttribute("sectionTitle", "Thêm phim");
        req.setAttribute("pageTitle", "Trang Admin - Thêm phim");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("add-film.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

//        RenderViewUtils.updateView(req, resp,
//                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();

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
        String filmGenreIDs = StringUtils.handlesInputString(req.getParameter("genre-ids"));
        String[] filmGenreIDArr = filmGenreIDs.split(" ");

        Film newFilm = new Film(filmName, filmPrice, filmDirector, filmActors, filmLength, filmDescription, filmTrailerLink,
                relativeFilePath);

        if (FileUploadUtils.uploadFile(req, fileName, "upload-img")) {
            filmServices.save(newFilm, filmGenreIDArr);

            resp.sendRedirect("admin");
        } else {
            req.setAttribute("fileUploadError", "Lỗi tải file lên!");
//            RenderViewUtils.updateView(req, resp, ContextPathUtils.getAdminPagesPath("add-film.jsp"));
            RenderViewUtils.renderViewToLayout(req, resp,
                    ContextPathUtils.getAdminPagesPath("add-film.jsp"),
                    ContextPathUtils.getLayoutPath("master.jsp"));
        }
    }

    @Override
    public void destroy() {
        filmServices = null;
    }
}
