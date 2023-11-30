package com.filmbooking.services;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;

import java.util.List;

public interface IFilmBookingServices {
    void openSession();
    void closeSession();
    List<FilmBooking> getAll();
    FilmBooking getByFilmBookingID(String id);
    List<FilmBooking> getAllByUser(User user);
    void save(FilmBooking filmBooking);
    void update(FilmBooking filmBooking);
    void delete(FilmBooking filmBooking);
}
