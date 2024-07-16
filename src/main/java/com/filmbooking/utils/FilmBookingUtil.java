package com.filmbooking.utils;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.net.http.HttpRequest;
import java.util.*;

public class FilmBookingUtil {
    public static String generateFilmBookingJson(FilmBooking filmBooking) {
        Gson gson = GSONUtils.getGson();

        if (filmBooking == null) {
            return "{}";
        }
        Showtime showtime = filmBooking.getShowtime();
        User user = filmBooking.getUser();
        Film film = showtime.getFilm();

        String filmBookingJson = gson.toJson(filmBooking);
        String showtimeJson = gson.toJson(showtime);
        String userJson = gson.toJson(user);
        String filmJson = gson.toJson(film);

        return String.format("{\"filmBooking\":%s, \"showtime\":%s, \"user\":%s, \"film\":%s}",
                filmBookingJson, showtimeJson, userJson, filmJson);
    }

    public static List<String> generateFilmBookingJsonList(List<FilmBooking> filmBookingList) {
        List<String> filmBookingJsonList = new ArrayList<>();
        for (FilmBooking filmBooking : filmBookingList) {
            filmBookingJsonList.add(generateFilmBookingJson(filmBooking));
        }
        return filmBookingJsonList;
    }

    public static void cancelFilmBooking(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        FilmBooking filmBooking = (FilmBooking) session.getAttribute("filmBooking");
        Showtime showtime = filmBooking.getShowtime();

        if (filmBooking.getBookedSeats() != null && showtime != null) {
            System.out.println(Arrays.toString(filmBooking.getBookedSeats()));
            CRUDServicesLogProxy<Showtime> showtimeServices = new CRUDServicesLogProxy<>(new ShowtimeServicesImpl(), request, Showtime.class);
            showtime.releaseSeats(filmBooking.getBookedSeats());
            showtimeServices.update(showtime);
        }

        FilmBooking newFilmBooking = new FilmBooking();
        newFilmBooking.setUser(loginUser);
        session.setAttribute("filmBooking", newFilmBooking);

    }
}
