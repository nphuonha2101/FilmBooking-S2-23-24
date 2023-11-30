package com.filmbooking.services;

import com.filmbooking.model.Showtime;

import java.util.HashMap;
import java.util.List;

public interface IShowtimeServices {
    void openSession();
    void closeSession();
    List<Showtime> getAll();

    Showtime getByID(String id);
    List<Showtime> getByFilmID(String filmID);
    HashMap<String, Showtime> getShowtimeAndShowtimeID();

    void save(Showtime showtime);

    void update(Showtime showtime);

    void delete(Showtime showtime);

    void bookSeats(Showtime showtime, String ...seats);
    HashMap<Long, Integer> countAvailableSeats();
}
