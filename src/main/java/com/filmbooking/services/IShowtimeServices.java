package com.filmbooking.services;

import com.filmbooking.model.Showtime;

import java.util.List;

public interface IShowtimeServices {
    List<Showtime> getAll();

    Showtime getByID(String id);
    List<Showtime> getByFilmID(String filmID);

    void save(Showtime showtime);

    void update(Showtime showtime);

    void delete(Showtime showtime);
}
