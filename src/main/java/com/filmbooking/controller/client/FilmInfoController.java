package com.filmbooking.controller.client;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Genre;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RedirectPageUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "filmInfo", value = "/film-info")
public class FilmInfoController extends HttpServlet {
    private IFilmServices filmServices;
    private IShowtimeServices showtimeServices;
    private String queryString;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        queryString = req.getQueryString();

        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();

        filmServices.openSession();
        showtimeServices.openSession();

        String filmID = req.getParameter("film-id");

        Film bookedFilm = filmServices.getByFilmID(filmID);

        // get film genre names
        StringBuilder filmGenreNames = new StringBuilder();

        for (Genre genre : bookedFilm.getGenreList()
        ) {
            if (filmGenreNames.length() > 1)
                filmGenreNames.append(", ").append(genre.getGenreName());
            else
                filmGenreNames.append(genre.getGenreName());
        }

        req.setAttribute("sectionTitle", "Thông tin đặt phim");
        req.setAttribute("pageTitle", "filmInfoTitle");

        req.setAttribute("filmData", bookedFilm);
        req.setAttribute("filmGenreNames", filmGenreNames.toString());

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("film-info.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        filmServices.closeSession();
        showtimeServices.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeServices.openSession();

        if (req.getSession().getAttribute("loginUser") == null) {
            RedirectPageUtils.redirectPage("login", queryString, req, resp);
            return;
        }
        String bookedShowtimeID = req.getParameter("showtime-id");
        Showtime bookedShowtime = showtimeServices.getByID(bookedShowtimeID);

        FilmBooking filmBooking = (FilmBooking) req.getSession(false).getAttribute("filmBooking");
        filmBooking.setShowtime(bookedShowtime);

        req.getSession(false).setAttribute("filmBooking", filmBooking);

        resp.sendRedirect("book-film");

        showtimeServices.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        showtimeServices = null;

    }
}
