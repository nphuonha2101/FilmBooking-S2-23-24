package com.filmbooking.services;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;

import java.util.List;

public interface IFilmBookingServices {
    List<FilmBooking> getAll();
    FilmBooking getByFilmBookingID(String id);
    List<FilmBooking> getAllByUsername(String username);
    void save(FilmBooking filmBooking);
    void update(FilmBooking filmBooking);
    void delete(FilmBooking filmBooking);
}
