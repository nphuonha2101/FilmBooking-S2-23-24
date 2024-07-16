package com.filmbooking.controller.customer.film;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Genre;
import com.filmbooking.model.Showtime;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.RedirectPageUtils;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "filmInfo", value = "/film-info")
public class FilmInfoController extends HttpServlet {
    private FilmServicesImpl filmServices;
    private String queryString;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        queryString = req.getQueryString();

        filmServices = new FilmServicesImpl();

        String filmSlug = StringUtils.handlesInputString(req.getParameter("film"));

        Film bookedFilm = filmServices.getBySlug(filmSlug);

        // get film genre names
        StringBuilder filmGenreNames = new StringBuilder();

        for (Genre genre : bookedFilm.getGenreList()
        ) {
            if (filmGenreNames.length() > 1)
                filmGenreNames.append(", ").append(genre.getGenreName());
            else
                filmGenreNames.append(genre.getGenreName());
        }


        Page filmInfoPage = new ClientPage(
                "filmInfoTitle",
                "film-info",
                "master"
        );
        filmInfoPage.putAttribute("sectionTitle", "Thông tin đặt phim");

        filmInfoPage.putAttribute("filmData", bookedFilm);
        filmInfoPage.putAttribute("filmGenreNames", filmGenreNames.toString());
        filmInfoPage.putAttribute("filmScores", bookedFilm.getFilmVoteScoresStr());
        filmInfoPage.putAttribute("totalFilmVotes", bookedFilm.getFilmVoteList().size());

        filmInfoPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CRUDServicesLogProxy<Showtime> showtimeServicesLogCRUD = new CRUDServicesLogProxy<>(new ShowtimeServicesImpl(), req, Showtime.class);

        if (req.getSession().getAttribute("loginUser") == null) {
            RedirectPageUtils.redirectPage("login", queryString, req, resp);
            return;
        }

        String bookedShowtimeID = StringUtils.handlesInputString(req.getParameter("showtime-id"));
        System.out.println("bookedShowtimeID = " + bookedShowtimeID);

        if (!(bookedShowtimeID.isEmpty() || bookedShowtimeID.isBlank())) {
            Showtime bookedShowtime = showtimeServicesLogCRUD.select(bookedShowtimeID);
            if (bookedShowtime != null) {
                FilmBooking filmBooking = (FilmBooking) req.getSession(false).getAttribute("filmBooking");

                // release old showtime
                Showtime oldShowtime = showtimeServicesLogCRUD.select(filmBooking.getShowtimeId());
                if (oldShowtime != null && filmBooking.getBookedSeats() != null) {
                    oldShowtime.releaseSeats(filmBooking.getBookedSeats());
                    showtimeServicesLogCRUD.update(oldShowtime);
                }

                // reset filmbooking and add new showtime
                filmBooking.resetFilmBooking();
                filmBooking.setShowtime(bookedShowtime);

                req.getSession(false).setAttribute("filmBooking", filmBooking);

                resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/auth/book-film"));
                return;
            }
        } else {
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    @Override
    public void destroy() {
        filmServices = null;
    }
}
