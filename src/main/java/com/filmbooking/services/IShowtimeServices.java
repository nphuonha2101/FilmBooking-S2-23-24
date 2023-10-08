package com.filmbooking.services;

import com.filmbooking.model.Showtime;

import java.util.List;

public interface IShowtimeServices {
    List<Showtime> getAll();

    Showtime getByID(String id);

    void save(Showtime showtime);

    void update(Showtime showtime);

    void delete(Showtime showtime);
}
