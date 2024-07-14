package com.filmbooking.utils;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.User;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
