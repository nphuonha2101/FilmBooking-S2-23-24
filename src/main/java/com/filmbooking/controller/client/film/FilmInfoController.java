package com.filmbooking.controller.client.film;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Genre;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RedirectPageUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "filmInfo", value = "/film-info")
public class FilmInfoController extends HttpServlet {
    private IFilmServices filmServices;
    private IShowtimeServices showtimeServices;
    private String queryString;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        queryString = req.getQueryString();

        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        String filmID = StringUtils.handlesInputString(req.getParameter("film-id"));

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

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        if (req.getSession().getAttribute("loginUser") == null) {
            RedirectPageUtils.redirectPage("login", queryString, req, resp);
            return;
        }

        String bookedShowtimeID = StringUtils.handlesInputString(req.getParameter("showtime-id"));
        System.out.println("bookedShowtimeID = " + bookedShowtimeID);

        if (!(bookedShowtimeID.isEmpty() || bookedShowtimeID.isBlank())) {

            Showtime bookedShowtime = showtimeServices.getByID(bookedShowtimeID);
            if (bookedShowtime != null) {

                FilmBooking filmBooking = (FilmBooking) req.getSession(false).getAttribute("filmBooking");
                filmBooking.setShowtime(bookedShowtime);

                req.getSession(false).setAttribute("filmBooking", filmBooking);

                resp.sendRedirect("book-film");
                return;
            }
        } else {
            resp.sendRedirect(req.getHeader("referer"));
        }
        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
